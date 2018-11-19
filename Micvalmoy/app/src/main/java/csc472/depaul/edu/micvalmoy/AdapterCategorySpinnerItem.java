package csc472.depaul.edu.micvalmoy;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import csc472.depaul.edu.micvalmoy.entity.Category;
import timber.log.Timber;

// https://stackoverflow.com/questions/24523715/multi-selection-spinner-in-android-without-alertdialog
// https://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView

public class AdapterCategorySpinnerItem extends ArrayAdapter<Category>
{
    private Context context;
    private LayoutInflater inflater;
    private List<csc472.depaul.edu.micvalmoy.entity.Category> itemsList;
    public Spinner mySpinner = null;

    public static String oneSpace =" ";
    public static int tikMark =0X2714;
    public static int crossMark =0X2715;
    public static int tikMarkAroundBox =0X2611;
    public static int crossMarkAroundBox =0X274E;
    public static String dash ="-";

    private SparseBooleanArray mSelectedItemsIds;



    public AdapterCategorySpinnerItem(Context context, int resource, List<Category> objects, Spinner mySpinner)
    {
        super(context, resource, objects);

        this.context = context;
        mSelectedItemsIds = new SparseBooleanArray();
        this.itemsList = objects;
        this.mySpinner = mySpinner;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(final int position, View convertView, ViewGroup parent)
    {
        String text = "";
        final ViewHolder holder;

        mySpinner.setPrompt(getContext().getString(R.string.choose_cat_spinner_title));


        if (convertView == null)
        {
            holder = new ViewHolder();
            inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_quiz_category, null, false);
            holder.name = convertView.findViewById(R.id.tvSpinnerItem);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        Timber.d("initialing  selected items");
       // selectView(position, itemsList.get(position).isSelected());


        /**
         * check position , if position is zero we put space on top of list of spinner
         */
/*        if ((position == 0))
            text = oneSpace;
        *//**
         * check position , if position is one we put cross mark before text to show that position used to be for clear all selected items on spinner
         *//*
        else if ((position == 1))
            text = "  " + String.valueOf((char) crossMarkAroundBox) + " " + itemsList.get(position).getName();
        *//**
         * check position , if position is two we put check mark before text to show that position used to be for select all items on spinner
         *//*
        else if ((position == 2))
            text = "  " + String.valueOf((char) tikMarkAroundBox) + " " + itemsList.get(position).getName();
        *//**
         * check position , if position is bigger than two we have to check that position is selected before or not and put check mark or dash before text
         *//*
        else
        {
            //mark as selected
        }*/

        text = itemsList.get(position).getName();
        holder.name.setText(text);
        holder.name.setTag(position);

        holder.name.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /**
                 * if you want open spinner after click on text for first time we have to open spinner programmatically
                 */
                mySpinner.performClick();
                int getPosition = (Integer) v.getTag();

                toggleSelection(getPosition);


                notifyDataSetChanged();

                /**
                 * if clicked position is one
                 * that means you want clear all select item in list
                 */
          /*      if (getPosition == 1)
                {
                    clearList();
                }
                */
                /**
                 * if clicked position is two
                 * that means you want select all item in list
                 */
  /*              else if (getPosition == 2)
                {
                    fillList();
                }*/
            }
        });
        return convertView;
    }


    /**
     * clear all items in list
     */
    public void clearList()
    {
        for (Category item : itemsList)
        {
            item.setSelected(false);
        }
        notifyDataSetChanged();
    }

    /**
     * select all items in list
     */
    public void fillList()
    {
        for (Category item : itemsList)
        {
            item.setSelected(true);
        }
        notifyDataSetChanged();
    }

    //----------------------------------------------------------------------------
    @Override
    public void remove(Category object) {
        itemsList.remove(object);
        notifyDataSetChanged();
    }


    @Nullable
    @Override
    public Category getItem(int position) {
        if (position == 0) {
            Category category = new Category();

            String catName = getContext().getString(R.string.create_new_category);
            category.setName("moya cat name");

            return category;
        }
        return super.getItem(position - 1);
    }
    //----------------------------------------------------------------------------


    //bad code ,,, delete sobnn
    public void  initSelectedItems(int position) {

/*        for(int i=0; i < itemsList.size(); i++){
            selectView(i, itemsList.get(i).isSelected());
        }*/
    }

    public List<Category> getItemsList() {
        return itemsList;
    }

    public void toggleSelection(int position) {
        itemsList.get(position).setSelected(!itemsList.get(position).isSelected());
        selectView(position, !mSelectedItemsIds.get(position));
    }

    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public void selectView(int position, boolean value) {
        String text = "";

        if (value) {
            text = "  " + String.valueOf((char) tikMark) + " " + itemsList.get(position).getName();
            mSelectedItemsIds.put(position, value);
        }
        else{
            text = "  " + String.valueOf(dash) + " " + itemsList.get(position).getName();
            mSelectedItemsIds.delete(position);
        }
        notifyDataSetChanged();
    }

    public int getSelectedCount() {
        return mSelectedItemsIds.size();
    }

    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }

    public List<Category> getSelectedItems() {
        SparseBooleanArray selectItemIds =  getSelectedIds();

        List<Category> items = new ArrayList<>();

        for(int i=0; i < mSelectedItemsIds.size(); i++){
            int position = mSelectedItemsIds.keyAt(i);

            items.add(itemsList.get(position));

            Timber.d("Element at %s, is %s",position, mSelectedItemsIds.get(position));
        }
        return items;
    }
    //------------------------------------------------------------------------




    /**
     * view holder
     */
    private class ViewHolder
    {
        private TextView name;
    }
}