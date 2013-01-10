package com.nhn.android.mapviewer;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhn.android.maps.NMapOverlay;
import com.nhn.android.maps.NMapOverlayItem;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.placeline.Activity.R;

public class NMapCalloutCustomOverlayView extends NMapCalloutOverlayView {

	private View mCalloutView;
	private TextView mCalloutText;
	private View mRightArrow;
	private View mLeftThumbnail;
	private View mAddPin;
	private Handler mHandler;

	public NMapCalloutCustomOverlayView(Context context, NMapOverlay itemOverlay, NMapOverlayItem item, Rect itemBounds, int thumbnailId, final Handler mHandler) {
		super(context, itemOverlay, item, itemBounds);

		this.mHandler = mHandler;
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li = (LayoutInflater)getContext().getSystemService(infService);
		li.inflate(R.layout.callout_overlay_view, this, true);

		mCalloutView = findViewById(R.id.callout_overlay);
		mAddPin = findViewById(R.id.icon_add_pin);
		mAddPin.setOnClickListener(new View.OnClickListener() { 
			public void onClick(View v) { 
				mHandler.sendEmptyMessage(0);
	       	} 
		}); 
		
		if(thumbnailId == R.drawable.detailed_pin_add_photo){
			mCalloutView.setVisibility(View.GONE);
		}
		else{
			mAddPin.setVisibility(View.GONE);
			mCalloutText = (TextView)mCalloutView.findViewById(R.id.callout_text);
			mRightArrow = findViewById(R.id.callout_rightArrow);
			mLeftThumbnail = (ImageView)findViewById(R.id.callout_leftThumbnail);
			mLeftThumbnail.setBackgroundResource(thumbnailId);

			mCalloutView.setOnClickListener(callOutClickListener);

			mCalloutText.setText(item.getTitle());

			if (item instanceof NMapPOIitem) {
				if (((NMapPOIitem)item).hasRightAccessory() == false) {
					mRightArrow.setVisibility(View.GONE);
				}
			}
		}
	}

	private final OnClickListener callOutClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			Log.d("########## [DEBUG] ##########", "callOutClickListener");
			if (mOnClickListener != null) {
				mOnClickListener.onClick(null, mItemOverlay, mOverlayItem);
			}
		}
	};

}
