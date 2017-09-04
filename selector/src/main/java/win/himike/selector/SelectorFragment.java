package win.himike.selector;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import win.himike.selector.entity.City;

import static win.himike.selector.BaseRegionActivity.CITY;
import static win.himike.selector.BaseRegionActivity.SELECTED;

/**
 * Created by HiMike on 2017/9/3.
 */

public class SelectorFragment extends Fragment {


    public static SelectorFragment newInstance(ArrayList<City> cityList, ArrayList<City> selected) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(CITY, cityList);
        args.putParcelableArrayList(SELECTED, selected);
        SelectorFragment fragment = new SelectorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView mRecyclerView;
    private ItemAdapter mAdapter;
    private ArrayList<City> mCity;
    private ArrayList<City> mSelected;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCity = getArguments().getParcelableArrayList(CITY);
        mSelected = getArguments().getParcelableArrayList(SELECTED);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSelected.clear();
        mCity.clear();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selector, container, false);
        createView(view);
        return view;
    }

    private void createView(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter = new ItemAdapter());
        mAdapter.setData(mCity);
    }

    class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
        private List<City> mData;

        public ItemAdapter() {
            mData = new ArrayList<>();
        }

        @Override
        public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selector, parent, false));
        }

        @Override
        public void onBindViewHolder(ItemAdapter.ViewHolder holder, int position) {
            holder.name.setText(mData.get(position).getName());
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public void setData(List<City> data) {
            mData = data;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView name;

            public ViewHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.name);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<City> cities = (ArrayList<City>) mData.get(getAdapterPosition()).getCity();
                        if (cities != null && !cities.isEmpty()) {
                            ArrayList<City> copy = new ArrayList<>(mSelected);
                            copy.add(mData.get(getAdapterPosition()));
                            getFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.container, SelectorFragment.newInstance(cities, copy))
                                    .addToBackStack(null)
                                    .commit();
                        } else {
                            if (getActivity() instanceof CallBack) {
                                mSelected.add(mData.get(getAdapterPosition()));
                                ((CallBack) getActivity()).onSelect(mSelected);
                            } else {
                                throw new RuntimeException("the activity should implement CallBack");
                            }
                        }
                    }
                });
            }
        }
    }
}
