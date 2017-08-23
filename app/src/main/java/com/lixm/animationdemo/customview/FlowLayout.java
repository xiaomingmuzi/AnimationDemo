package com.lixm.animationdemo.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioGroup;

import java.util.ArrayList;

/**
 * @author Lixm
 * @date 2017/6/13
 * @detail 理财超市选择开户行
 */

/*
 * 整体思路： 自定义FlowLayout: 1.根据list的size往里面动态添加TextView 2.定义变量:
 * a.horizontalSpacing：子view间的水平间距 b.verticalSpacing: line间的垂直间距
 * c.lineList:用于存放每个line 3.定义Line类：用来记录当前行中的子view，并且提供width和height;
 * width:表示所有子view的宽加上中间的空隙值，在每次添加子view的时候都会更新 height:始终等于当前行中最高子view的高度
 * 4.遍历所有子view过程并判断如下： a.先测量当前childView，保证能获取到宽度和高度
 * b.如果当前line中还没子view，则直接放入，不用判断长度
 * c.当前line中已经有子view的情况：如果line的宽度+horizontalSpacing+child.getMeasuredWidth
 * 大于当前总宽度则立即存储当前line，然后重新new Line(),并将子view加入到new的Line中；
 * 如果不大于总宽度，则将当前childView放入line中；
 * d.注意：每次往line中放子view的时候都需要判断当前子view是否是最后一个；如果是最后一个子 view了，那么久需要将当前line存储，
 *
 * 5.依次摆放lineList中每个line中保存的子view: a.遍历lineList，取出当前line;
 * b.遍历当前line，取出当前childView;
 * c.如果line是第一行：对childView进行摆放如下：如果childView是第一个，则left是getPaddingLeft,
 * top是getPaddingTop;right是left+自身的宽，bottom是top+自身的高；
 * 如果childView不是第一个，则取出前一个子view(记录为lastChild);则当前childView的left是
 * lastChild的right+horizontalSpacing;top和lastChild一样，right是自身的left+自身的宽；
 * bottom是和lastChild一样；
 * d.如果当前line是第二行，则子view摆放的top需要在getPaddingTop的基础上+line的高度+verticalSpacing;
 * e.最后，由于每行的子view摆放出来之后会有剩余的空间，所以获取剩余的空间，然后除以
 * 子view的数量，得到每个子view应该重新分配的值，对他们的宽重新赋值，并且这个赋值的 操作是在摆放子view之前，因为摆放的时候用到了他们的宽度
 */
public class FlowLayout extends RadioGroup {

    /**
     * 默认的间距
     */
    private int DEFAULT_SPACING = 25;
    /**
     * 子view水平方向的间距
     */
    private int horizontalSpacing = 40;
    /**
     * 每行之间的垂直间距
     */
    private int verticalSpacing = 60;
    /**
     * 用来保存每一行line
     */
    private ArrayList<Line> lineList = new ArrayList<Line>();

    /**
     * 有多少行
     */
    int lineNumber = 0;


    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context) {
        super(context);
    }

    /**
     * 分行和测量FlowLayout的宽高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        lineList.clear();// 开始分行前先清空之前的list

        int width = MeasureSpec.getSize(widthMeasureSpec);// 获得控件的宽度,是包含paddingLeft和paddingRight
        // 这个宽才是我们需要比较用的宽
        int noPaddingWidth = width - getPaddingLeft() - getPaddingRight();// 获取除去padding后的width
        int height = MeasureSpec.getSize(0);// 获取控件的高度

        Line line = null;// 声明为局部变量

        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);// 获得当前子view
            // 1.先测量子view，是为了保证一定能够获取到宽高
            childView.measure(0, 0);// 系统会按照自己的规则去测量

            if (line == null) {
                line = new Line();
            }

            // 2.如果当前line还没有子view，则直接放入，不用判断,因为要保证每行至少有一个view
            if (line.getViewList().size() == 0) {
                line.addView(childView);
            } else {
                // 当前line中有子view
                if (line.getWidth() + horizontalSpacing + childView.getMeasuredWidth() > noPaddingWidth) {
                    // 表示需要换行,先存放之前的line，然后在重新new Line
                    lineList.add(line);

                    line = new Line();
                    line.addView(childView);
                    // 如果当前childView是最后一个子view，则需要保存line
                    if (i == (getChildCount() - 1)) {
                        lineList.add(line);
                    }
                } else {
                    // 不需要换行,直接添加到当前line
                    line.addView(childView);
                    // 如果当前childView是最后一个子view，则需要保存line
                    if (i == (getChildCount() - 1)) {
                        lineList.add(line);
                    }
                }
            }
        }
        line = null;

        lineNumber = lineList.size();

        //TODO 如果需要只显示2行，加上这个判断。在onLayout 方法中，将2个TODO定位的代码放开即可
//      if (lineNumber > 2) {
//          lineNumber = 2;
//      }

        // 计算当前FlowLayout的总高度
        for (int i = 0; i < lineNumber; i++) {
            height += lineList.get(i).getHeight();
        }

        if (lineList.size() > 0) {
            height += verticalSpacing * (lineNumber - 1) + getPaddingTop() + getPaddingBottom();

            // 告诉系统我要申请这么多的宽高
            setMeasuredDimension(width, height);
        }
    }

    /**
     * 摆放所有line中的子view
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();

        for (int i = 0; i < lineList.size(); i++) {

            //TODO
//          if (i < 2) {

            Line line = lineList.get(i);// 获取当前的line
            ArrayList<View> viewList = line.getViewList();// 获取当前line的子view集合

            // 每行的top是不断增加的
            if (i != 0) {
                Line preLine = lineList.get(i - 1);// 获取上一行
                paddingTop += preLine.getHeight() + verticalSpacing;
            }

            // 获取每行的留白
            float remainSpace = getMeasuredWidth() - getPaddingLeft() - getPaddingRight() - line.getWidth();
            // 计算出每个子view平分到的留白
            float perSpace = remainSpace / viewList.size();

            for (int j = 0; j < viewList.size(); j++) {
                View childView = viewList.get(j);
                // 将每个子view得到的留白重新添加到自己的宽度，然后重新测量
                int widthMeasureSpec = MeasureSpec.makeMeasureSpec((int) (childView.getMeasuredWidth() + perSpace),
                        MeasureSpec.EXACTLY);
                childView.measure(widthMeasureSpec, 0);

                if (j == 0) {
                    // 如果是第一个子view，则靠左边摆放
                    childView.layout(paddingLeft, paddingTop, paddingLeft + childView.getMeasuredWidth(),
                            paddingTop + childView.getMeasuredHeight());
                } else {
                    // 后面子view，总是比前一个要多个horizontalSpacing
                    View preView = viewList.get(j - 1);// 获取前一个子view
                    int left = preView.getRight() + horizontalSpacing;// 前一个view的right+horizontalSpacing
                    childView.layout(left, preView.getTop(), left + childView.getMeasuredWidth(),
                            preView.getBottom());
                }
            }

            //TODO
//          }

        }
    }

    public int getHorizontalSpacing() {
        return horizontalSpacing;
    }

    public void setHorizontalSpacing(int horizontalSpacing) {
        this.horizontalSpacing = horizontalSpacing;
    }

    /**
     * 用来保存每行的子view，代表一行
     *
     * @author Administrator
     *
     */
    class Line {
        ArrayList<View> viewList;// 用于记录行内所有的子view
        private int width;// 表示所有子view的宽加上中间的horizontalSpacing
        private int height;// 记录自身的高度

        public Line() {
            viewList = new ArrayList<View>();
        }

        /**
         * 保存子view到viewList中
         */
        public void addView(View view) {
            if (!viewList.contains(view)) {
                viewList.add(view);
            }

            // 更新width和height
            if (viewList.size() == 1) {
                // 只有1个子veiw，则line的width就是当前子view的宽
                width = view.getMeasuredWidth();
            } else {
                // 有多个子veiw的时候，除了加上当前view的宽，还要加上horizontalSpacing
                width += view.getMeasuredWidth() + getHorizontalSpacing();
            }
            // 总是取子veiw中最高的作为自己高度
            height = Math.max(height, view.getMeasuredHeight());
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public ArrayList<View> getViewList() {
            return viewList;
        }
    }

}
