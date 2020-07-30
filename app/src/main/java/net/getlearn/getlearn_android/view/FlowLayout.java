package net.getlearn.getlearn_android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/21------更新------
 */

public class FlowLayout extends ViewGroup {

    private int horizontalSpace = 15;
    private int verticalSpace = 15;
    //用来保存所有的row对象
    private ArrayList<Row> rowList = new ArrayList<>();
    private int noPaddingWidth;

    public void setVerticalSpace(int verticalSpace) {
        this.verticalSpace = verticalSpace;
    }

    public void setHorizontalSpace(int horizontalSpace) {
        this.horizontalSpace = horizontalSpace;
    }

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //由于某些原因。onMeasure会执行多次
        rowList.clear();

        //1.计算总宽度
        int width = MeasureSpec.getSize(widthMeasureSpec);
        //2.计算出实际用于比较的宽度,就是减去左右padding的宽度
        noPaddingWidth = width - getPaddingLeft() - getPaddingRight();

        //3.编写for循环，去计算每一行
        Row row = new Row();//预先创建row对象，用来存放子View
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            //强制测量，为了保证能够拿到宽高
            child.measure(0,0);

            //a.特殊情况，如果当前row中还没有子View，那么则不用比较了，直接放入
            //原因是不能允许空行的存在,即时比较的结果大于noPaddingWidth，也需要放入
            if(row.viewList.size()==0){
                row.addRowView(child);
            }else if(child.getMeasuredWidth()+row.rowWidth+horizontalSpace > noPaddingWidth){
                //b.如果child的宽 + 当前行的宽 + 水平间距 > noPaddingWidth,说明要换行
                //需要先保存之前的row对象
                rowList.add(row);
                //创建新的row对象来存储child
                row = new Row();
                row.addRowView(child);
            }else {
                //c.说明不大于，应该放入当前行
                row.addRowView(child);
            }
        }

        //d. for循环结束后，会丢失最后一行，需要手动存储
        rowList.add(row);

        //for循环结束了，rowList中存储了所有的Row对象，而每个row对象又记录了自己行的所有的子View
        //为了让FlowLayout能够摆放的下这么多的行，需要计算一下总共的高度
        int height = getPaddingBottom() + getPaddingTop();
        //再加上所有行的高度
        for (Row r : rowList){
            height += r.rowHeight;
        }
        //再加上垂直间距
        height += (rowList.size()-1)*verticalSpace;

        //设置高度
        setMeasuredDimension(width, height);
    }

    private boolean isAlignRight = true;//是否右边对齐

    public void setAlignRight(boolean b){
        isAlignRight = b;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();

        //遍历所有的Row
        for (int i = 0; i < rowList.size(); i++) {
            Row row = rowList.get(i);

            //从第1行开始，每一行的top都是上一行的top + 上一行的高度 + 垂直间距
            if(i>0){
                paddingTop += rowList.get(i-1).rowHeight + verticalSpace;
            }

            //1.计算出空白区域的值
            int remainSpace = noPaddingWidth - row.rowWidth;
            //2.计算每个子View分到多少
            if(row.viewList.size()==0)return;
            int perSpace = remainSpace / row.viewList.size();

            //遍历row的每一个子View
            for (int j = 0; j < row.viewList.size(); j++) {
                View view = row.viewList.get(j);

                if(isAlignRight){
                    //3.将perSpace增加到每个View的宽度上
                    view.setPadding(view.getPaddingLeft()+perSpace/2, view.getPaddingTop()
                            , view.getPaddingRight()+perSpace/2, view.getPaddingBottom());
                    view.measure(0,0);//不主动测量，不生效
                }


                if(j==0){
                    view.layout(paddingLeft, paddingTop, paddingLeft+view.getMeasuredWidth(),
                            paddingTop+view.getMeasuredHeight());
                }else {
                    //后面的View，参考前一个的right
                    View preView = row.viewList.get(j-1);
                    int left = preView.getRight() + horizontalSpace;
                    view.layout(left, preView.getTop(), left+view.getMeasuredWidth(),
                            preView.getBottom());
                }

            }
        }
    }


    /**
     * 专门用来封装每一行的数据，子View的集合，宽和高
     */
    class Row{
        public ArrayList<View> viewList = new ArrayList<>();
        public int rowWidth;//当前行的宽，是指当前行所有子View的宽度 + 所有的水平间距
        public int rowHeight;//当前行的高度,就是任意子View的高度

        /**
         * 添加一个View对象到viewList中
         * @param view
         */
        public void addRowView(View view){
            viewList.add(view);

            //更新宽度和高度
            if(viewList.size()==1){
                //说明view是第一个子View
                rowWidth = view.getMeasuredWidth();
            }else {
                //说明不是第一个，还需要加上水平间隔
                rowWidth += view.getMeasuredWidth() + horizontalSpace;
            }

            rowHeight = view.getMeasuredHeight();
        }
    }

    FlowLayoutAdapter adapter;

    /**
     * 设置adpater
     * @param adapter
     */
    public void setAdapter(FlowLayoutAdapter adapter){
        this.adapter = adapter;

        //从适配器中取出每个条目的view，添加进来
        for (int i = 0; i < adapter.getCount(); i++) {
            View view = adapter.getView(this, i);
            addView(view);
        }
    }

    public interface FlowLayoutAdapter{
        /**
         * 返回条目数量
         * @return
         */
        int getCount();

        /**
         * 返回条目View
         * @return
         */
        View getView(ViewGroup container, int position);
    }
}
