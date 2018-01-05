package com.preethzcodez.ecommerceexample.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.preethzcodez.ecommerceexample.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Preeth on 1/6/2018.
 */

public class FilterItemListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;
    List<String> sizeFilter, colorFilter;

    public FilterItemListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData, List<String> sizeFilter, List<String> colorFilter) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this.sizeFilter = sizeFilter;
        this.colorFilter = colorFilter;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.sort_filter_listitem, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.name);

        // Set Tick Visibility
        ImageView img = (ImageView) convertView.findViewById(R.id.tick);
        switch (groupPosition) {
            case 0:
                try {
                    if (sizeFilter.contains(childText)) {
                        img.setVisibility(View.VISIBLE);
                    } else {
                        img.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    img.setVisibility(View.GONE);
                }
                break;

            case 1:
                try {
                    if (colorFilter.contains("'"+childText+"'")) {
                        img.setVisibility(View.VISIBLE);
                    } else {
                        img.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    img.setVisibility(View.GONE);
                }
                break;
        }

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.sort_filter_listitem, null);
        }

        if (isExpanded) {

        } else {

        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.name);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setTextColor(Color.BLACK);
        lblListHeader.setTextSize(16);
        lblListHeader.setText(headerTitle);

        ImageView tick = (ImageView) convertView.findViewById(R.id.tick);
        tick.setVisibility(View.GONE);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}