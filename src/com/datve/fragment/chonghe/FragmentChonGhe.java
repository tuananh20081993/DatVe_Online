package com.datve.fragment.chonghe;

import android.content.Intent;
import android.os.Bundle;

import java.lang.reflect.InvocationTargetException;

import com.datve.data.parse.tuyenxe.TuyenXeAtivity;
import com.datve_online.request.ThongTinChiTiet;
import com.example.datve_online.R;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;

public class FragmentChonGhe extends Fragment{

	
	private FragmentTabHost fragmentTabHost;
	private Button tieptuc,thaydoi;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		View rootView=inflater.inflate(R.layout.layout_fragment_main, container,
				false);
		
		fragmentTabHost=(FragmentTabHost) rootView.findViewById(R.id.tabhost);
		fragmentTabHost.setup(getActivity(), getChildFragmentManager(), R.id.tabFrameLayout);
		fragmentTabHost.addTab(fragmentTabHost.newTabSpec("Ghế Tầng Dưới").setIndicator("Ghế Tầng Dưới"),FragmentGheTangDuoi.class,null);
		fragmentTabHost.addTab(fragmentTabHost.newTabSpec("Ghế Tầng Trên").setIndicator("Ghế Tầng Trên"),FragmentGheTangTren.class,null);

		return fragmentTabHost;




	}
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		fragmentTabHost = null;
	}

	

}
