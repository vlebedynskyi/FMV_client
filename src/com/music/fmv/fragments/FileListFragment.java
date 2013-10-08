/* 
 * Copyright (C) 2012 Paul Burke
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */ 

package com.music.fmv.fragments;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.music.fmv.R;
import com.music.fmv.activities.FileChooserActivity;
import com.music.fmv.adapters.FileListAdapter;
import com.music.fmv.utils.FileLoader;

import java.io.File;
import java.util.List;

/**
 * Fragment that displays a list of Files in a given path.
 * 
 * @version 2012-10-28
 * 
 * @author paulburke (ipaulpro)
 * 
 */
public class FileListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<List<File>> {

	private static final int LOADER_ID = 0;

	private FileListAdapter mAdapter;
	private String mPath;

	/**
	 * Create a new instance with the given file path.
	 * 
	 * @param path The absolute path of the file (directory) to display.
	 * @return A new Fragment with the given file path. 
	 */
	public static FileListFragment newInstance(String path) {
		FileListFragment fragment = new FileListFragment();
		Bundle args = new Bundle();
		args.putString(FileChooserActivity.PATH, path);
		fragment.setArguments(args);

		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mAdapter = new FileListAdapter(getActivity());
		mPath = getArguments() != null ? getArguments().getString(
				FileChooserActivity.PATH) : Environment
				.getExternalStorageDirectory().getAbsolutePath();
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup layout = (ViewGroup) super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup progressContainer = (ViewGroup) layout.getChildAt(0);
        ((ProgressBar)progressContainer.getChildAt(0)).setIndeterminateDrawable(getResources().getDrawable(R.drawable.blue_rotate));
        ListView lv = (ListView) layout.findViewById(android.R.id.list);
        View chooseHeader = inflater.inflate(R.layout.file_list_header, null, false);
        lv.addHeaderView(chooseHeader);
        return layout;
    }

    @Override
	public void onActivityCreated(Bundle savedInstanceState) {
		setEmptyText(getString(R.string.empty_directory));
		setListAdapter(mAdapter);
		setListShown(false);

		getLoaderManager().initLoader(LOADER_ID, null, this);

		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
        if (position == 0){
            Toast.makeText(getActivity(), "Folder picked", Toast.LENGTH_SHORT).show();
            return;
        }

        HeaderViewListAdapter adapter = (HeaderViewListAdapter) l.getAdapter();
		if (adapter != null) {
			File file = (File) adapter.getItem(position);
			mPath = file.getAbsolutePath();
			((FileChooserActivity) getActivity()).onFileSelected(file);
		}
	}

	@Override
	public Loader<List<File>> onCreateLoader(int id, Bundle args) {
		return new FileLoader(getActivity(), mPath);
	}

	@Override
	public void onLoadFinished(Loader<List<File>> loader, List<File> data) {
		mAdapter.setListItems(data);

		if (isResumed())
			setListShown(true);
		else
			setListShownNoAnimation(true);
	}

	@Override
	public void onLoaderReset(Loader<List<File>> loader) {
		mAdapter.clear();
	}
}