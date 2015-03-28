package org.xiagbalao.tree;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xiangbalao.tree.R;

import java.util.ArrayList;
import java.util.List;


public  class TreeViewAdapter extends ArrayAdapter<TreeElement> {
        private int img_leaf = R.drawable.icon_user;// 没有子节点的节点图标
        private int img_expand = R.drawable.outline_list_expand;// 展开的图标
        private int img_collapse = R.drawable.outline_list_collapse;// 收缩的图标
        private int img_tree_space_1 = R.drawable.tree_space_1;// 连接线
        private int img_tree_space_2 = R.drawable.tree_space_2;
    
        private Context context;
        private LayoutInflater mInflater;
        private List<TreeElement> mfilelist;
        private int viewResourceId;
    
        public TreeViewAdapter(Context context, int viewResourceId,
                List<TreeElement> objects) {
            super(context, viewResourceId, objects);
            this.context = context;
            this.mInflater = LayoutInflater.from(context);
            this.mfilelist = objects;
            this.viewResourceId = viewResourceId;
        }
    
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            convertView = mInflater.inflate(viewResourceId, null);
            holder = new ViewHolder();
            holder.caption = (TextView) convertView.findViewById(R.id.caption);
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            holder.space = (LinearLayout) convertView.findViewById(R.id.space);
            convertView.setTag(holder);
            TreeElement treeElement = mfilelist.get(position);
    
            int level = treeElement.getLevel();
            if (level == 0) {// 根节点
    
            } else {
                ArrayList<Integer> spaceList = treeElement.getSpaceList();
    
                // 绘制前面的组织架构线条
                for (int i = 0; i < spaceList.size(); i++) {
                    ImageView img = new ImageView(context);
                    img.setImageResource(spaceList.get(i));
                    holder.space.addView(img);
                }
                ImageView img = new ImageView(context);
                // 节点图标
                if (treeElement.isLastSibling()) {
                    img.setImageResource(img_tree_space_2);
                } else {
                    img.setImageResource(img_tree_space_1);
                }
    
                holder.space.addView(img);
            }
            if (treeElement.isHasChild()) {
                if (treeElement.isExpanded()) {
                    holder.icon.setImageResource(img_expand);
                } else {
                    holder.icon.setImageResource(img_collapse);
                }
            } else {
                holder.icon.setImageResource(img_leaf);
            }
            holder.caption.setText(treeElement.getCaption());// 设置标题
            return convertView;
        }
    
        class ViewHolder {
            LinearLayout space;
            TextView caption;
            ImageView icon;
        }
    }