package org.xiagbalao.tree;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import org.xiangbalao.tree.R;

import java.util.ArrayList;

public class MainActivity extends Activity {
	private ListView lv_tree;
	private TreeViewAdapter treeViewAdapter;
	private ArrayList<TreeElement> mRootList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv_tree = (ListView) findViewById(R.id.lv_tree);
		mRootList = new ArrayList<TreeElement>();
		treeViewAdapter = new TreeViewAdapter(this, R.layout.atom_tree,
				mRootList);
		lv_tree.setAdapter(treeViewAdapter);
		lv_tree.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				System.out.println("position:" + position);
				if (!mRootList.get(position).isHasChild()) {
					Toast.makeText(MainActivity.this,
							mRootList.get(position).getCaption(),
							Toast.LENGTH_SHORT).show();
					return;
				}

				if (mRootList.get(position).isExpanded()) {
					mRootList.get(position).setExpanded(false);
					TreeElement element = mRootList.get(position);
					ArrayList<TreeElement> temp = new ArrayList<TreeElement>();

					for (int i = position + 1; i < mRootList.size(); i++) {
						if (element.getLevel() >= mRootList.get(i).getLevel()) {
							break;
						}
						temp.add(mRootList.get(i));
					}
					mRootList.removeAll(temp);
					treeViewAdapter.notifyDataSetChanged();
				} else {
					TreeElement obj = mRootList.get(position);
					obj.setExpanded(true);
					int level = obj.getLevel();
					int nextLevel = level + 1;

					ArrayList<TreeElement> tempList = obj.getChildList();

					for (int i = tempList.size() - 1; i > -1; i--) {
						TreeElement element = tempList.get(i);
						element.setLevel(nextLevel);
						element.setExpanded(false);
						mRootList.add(position + 1, element);
					}
					treeViewAdapter.notifyDataSetChanged();
				}
			}
		});

		TreeElement rootElement = new TreeElement("root", "root", "1", true,
				false);

		TreeElement treeElement1 = new TreeElement("node1", "节点1", "1", true,
				false);
		TreeElement treeElement2 = new TreeElement("node2", "节点2", "2", false,
				false);
		TreeElement treeElement3 = new TreeElement("node3", "节点3", "1", false,
				true);
		TreeElement treeElement4 = new TreeElement("node3", "节点4", "1", true,
				true);

		TreeElement treeElement1_1 = new TreeElement("node14", "节点1_1", "1",
				true, true);
		TreeElement treeElement1_2 = new TreeElement("node14", "节点1_2", "1",
				false, true);
		TreeElement treeElement1_3 = new TreeElement("node14", "节点1_3", "1",
				true, true);
		TreeElement treeElement1_1_1 = new TreeElement("node14", "节点1_1_1",
				"1", false, true);
		TreeElement treeElement1_3_1 = new TreeElement("node14", "节点1_3_1",
				"1", true, true);
		TreeElement treeElement1_3_2 = new TreeElement("node14", "节点1_3_2",
				"1", false, true);
		TreeElement treeElement1_3_1_1 = new TreeElement("node14", "节点1_3_1_1",
				"1", false, true);

		TreeElement treeElement4_1 = new TreeElement("node14", "节点4_1", "1",
				true, true);
		TreeElement treeElement4_2 = new TreeElement("node14", "节点4_2", "1",
				false, true);
		TreeElement treeElement4_3 = new TreeElement("node14", "节点4_3", "1",
				true, true);
		TreeElement treeElement4_1_1 = new TreeElement("node14", "节点4_1_1",
				"1", false, true);
		TreeElement treeElement4_3_3 = new TreeElement("node14", "节点4_3_3",
				"1", false, true);
		mRootList.add(rootElement);
		rootElement.addChild(treeElement1);
		rootElement.addChild(treeElement2);
		rootElement.addChild(treeElement3);
		rootElement.addChild(treeElement4);
		treeElement1.addChild(treeElement1_1);
		treeElement1.addChild(treeElement1_2);
		treeElement1.addChild(treeElement1_3);
		treeElement1_1.addChild(treeElement1_1_1);
		treeElement1_3.addChild(treeElement1_3_1);
		treeElement1_3.addChild(treeElement1_3_2);
		treeElement1_3_1.addChild(treeElement1_3_1_1);
		treeElement4.addChild(treeElement4_1);
		treeElement4.addChild(treeElement4_2);
		treeElement4.addChild(treeElement4_3);
		treeElement4_1.addChild(treeElement4_1_1);
		treeElement4_3.addChild(treeElement4_3_3);

		treeViewAdapter.notifyDataSetChanged();
	}




}