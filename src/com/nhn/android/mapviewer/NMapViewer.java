/* 
 * NMapViewer.java $version 2010. 1. 1
 * 
 * Copyright 2010 NHN Corp. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */

package com.nhn.android.mapviewer;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.nhn.placeline.constants.Constants;
import com.nhn.placeline.util.FriendsListAdapter;
import com.nhn.placeline.vo.Pin;
import com.nhn.placeline.vo.User;
import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapCompassManager;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapLocationManager;
import com.nhn.android.maps.NMapOverlay;
import com.nhn.android.maps.NMapOverlayItem;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.nmapmodel.NMapPlacemark;
import com.nhn.android.maps.overlay.NMapCircleData;
import com.nhn.android.maps.overlay.NMapCircleStyle;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.maps.overlay.NMapPathData;
import com.nhn.android.maps.overlay.NMapPathLineStyle;
import com.nhn.placeline.Activity.DetailedPinActivity;
import com.nhn.placeline.Activity.R;
import com.nhn.android.mapviewer.overlay.NMapCalloutCustomOverlay;
import com.nhn.android.mapviewer.overlay.NMapCalloutOverlay;
import com.nhn.android.mapviewer.overlay.NMapMyLocationOverlay;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;
import com.nhn.android.mapviewer.overlay.NMapPathDataOverlay;
 
/** 
 * Sample class for map viewer library.
 * 
 * @author kyjkim
 */
public class NMapViewer extends NMapActivity implements OnClickListener {

	private MapContainerView mMapContainerView;
	
	private LinearLayout friendslistLayout;
	private Animation slideLeftAnim;
	private Animation slideRightAnim;
	private boolean isFriendsListOpen = false;
	private View mapViewLayout;
	
	private ArrayList<User> friends;
	
	private NMapView mMapView;
	private NMapController mMapController;
	private ImageView buttonCurrentLocation;
	private ImageView buttonAddPin;
	private ImageView buttonFriendsList;
	
	private boolean flagMyLocationOnOff;
	private boolean addPinOnOff;
	private SharedPreferences mPreferences;
	private NMapOverlayManager mOverlayManager;
	private NMapMyLocationOverlay mMyLocationOverlay;
	private NMapLocationManager mMapLocationManager;
	private NMapCompassManager mMapCompassManager;
	private NMapViewerResourceProvider mMapViewerResourceProvider;
	private NMapPOIdataOverlay mFloatingPOIdataOverlay;
	private NMapPOIitem mFloatingPOIitem;
	private NMapPathDataOverlay pathDataOverlay;

	private ArrayList<Pin> pinList;
	private String userId;
	private String groupId;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (Constants.USE_XML_LAYOUT) {
			setContentView(R.layout.map_main);
			mMapView = (NMapView)findViewById(R.id.mapView);
		} else {
			// create map view
			mMapView = new NMapView(this);

			// create parent view to rotate map view
			mMapContainerView = new MapContainerView(this);
			mMapContainerView.addView(mMapView);

			// set the activity content to the parent view
			setContentView(mMapContainerView);
		}
		
		initMap();
		initButtons();
		initInstance(); 
		slideFriendList();
		
		mapViewLayout = (LinearLayout)findViewById(R.id.mapview_layout);
		friendslistLayout =(LinearLayout)findViewById(R.id.friendslist);
		
		friends = new ArrayList<User>();
		Intent intent = getIntent();
		
		getFriendsList(userId);
		FriendsListAdapter friendAdapter = new FriendsListAdapter(this, R.layout.friend_list_partition, friends);
		ListView listView = (ListView)findViewById(R.id.friendlist_view);
		listView.setAdapter(friendAdapter);
	}
	   
	
	private void getFriendsList(String userId) {
		//DB 작업 해야할 부분
		friends.add(new User("윤영제", "016-9611-7061", R.drawable.user_4));
		friends.add(new User("김성호", "016-9611-7061", R.drawable.user_2));
		friends.add(new User("백준선", "016-9611-7061", R.drawable.user_3));
		friends.add(new User("윤홍경", "016-9611-7061", R.drawable.user_1));
	}


	private void initInstance(){
		Intent intent = getIntent();
		userId = intent.getStringExtra("userId");
		groupId = intent.getStringExtra("groupId");
		Log.d("########## [DEBUG] ##########"," intent vars - userId : " + userId + " / groupId : " + groupId);
		
		pinList = new ArrayList<Pin>();
		User user = new User("백준선", "010-6848-3855", R.drawable.photo_3);
		Pin newPin1 = new Pin("한라산", 1, user, 126.4085f, 33.2480f, R.drawable.ic_launcher);
		Pin newPin2 = new Pin("그린팩토리", 1, user, 126.4092f, 33.2480f, R.drawable.ic_launcher);
		Pin newPin3 = new Pin("신라호텔", 1, user, 126.4087f, 33.2491f, R.drawable.ic_launcher);
		Pin newPin4 = new Pin("성산일출봉", 1, user, 126.4090f, 33.2484f, R.drawable.ic_launcher);
		
		pinList.add(newPin1);
		pinList.add(newPin2);
		pinList.add(newPin3);
		pinList.add(newPin4);
		
		for(int i=0; i<pinList.size(); i++){
			putPOIdataOverlay(pinList.get(i));
		}
	}
	
	
	private void printCurrentLocation(){
		NGeoPoint center = mMapController.getMapCenter();
		Log.d("########## [DEBUG] ##########"," GPS Location : " + center.getLongitude() + " / " + center.getLatitude());
	}

	
	private void initButtons(){
		buttonCurrentLocation = (ImageView) findViewById(R.id.imageview_currentlocation);
		buttonCurrentLocation.setOnClickListener(this);
		buttonCurrentLocation.setBackgroundResource(R.drawable.ic_my_location_default);
		
		buttonAddPin = (ImageView) findViewById(R.id.imageview_addpin);
		buttonAddPin.setOnClickListener(this);
		
		buttonFriendsList = (ImageView) findViewById(R.id.imageview_friend);
		buttonFriendsList.setOnClickListener(this);
	} 
	
	private void initMap(){
		// set a registered API key for Open MapViewer Library
		mMapView.setApiKey(Constants.API_KEY);

		// initialize map view
		mMapView.setClickable(true);
		
		mMapView.setEnabled(true);
		mMapView.setFocusable(true);
		mMapView.setFocusableInTouchMode(true);
		mMapView.requestFocus();

		// register listener for map state changes
		mMapView.setOnMapStateChangeListener(onMapViewStateChangeListener);
		mMapView.setOnMapViewTouchEventListener(onMapViewTouchEventListener);
		
		mMapView.setOnMapViewDelegate(onMapViewTouchDelegate);

		// use map controller to zoom in/out, pan and set map center, zoom level etc.
		mMapController = mMapView.getMapController();

		// create resource provider
		mMapViewerResourceProvider = new NMapViewerResourceProvider(this);

		// set data provider listener
		super.setMapDataProviderListener(onDataProviderListener);

		// create overlay manager
		mOverlayManager = new NMapOverlayManager(this, mMapView, mMapViewerResourceProvider);
		// register callout overlay listener to customize it.
		mOverlayManager.setOnCalloutOverlayListener(onCalloutOverlayListener);
		// register callout overlay view listener to customize it.
		mOverlayManager.setOnCalloutOverlayViewListener(onCalloutOverlayViewListener);

		// location manager
		mMapLocationManager = new NMapLocationManager(this);
		mMapLocationManager.setOnLocationChangeListener(onMyLocationChangeListener);

		// compass manager
		mMapCompassManager = new NMapCompassManager(this);

		// create my location overlay
		mMyLocationOverlay = mOverlayManager.createMyLocationOverlay(mMapLocationManager, mMapCompassManager);
		
		
		flagMyLocationOnOff = false;
		addPinOnOff = false;
	}
	
	public void slideFriendList() {
		slideLeftAnim = AnimationUtils.loadAnimation(NMapViewer.this, R.anim.translate_left);
		slideRightAnim = AnimationUtils.loadAnimation(NMapViewer.this, R.anim.translate_right);
		
		ShowFriendsListener animListener = new ShowFriendsListener();
		slideLeftAnim.setAnimationListener(animListener);
		slideRightAnim.setAnimationListener(animListener);
		
	}
	
	private class ShowFriendsListener implements AnimationListener {
		@Override
		public void onAnimationEnd(Animation animation) {
			if(isFriendsListOpen){
				isFriendsListOpen = false;
			} else {
				isFriendsListOpen = true;
				friendslistLayout.bringToFront();
				mapViewLayout.setTranslationX(390);
			}
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
		}

		@Override
		public void onAnimationStart(Animation animation) {
			
		}
		
	}
	// 버튼 리스너 
	@Override
	public void onClick(View button) {
	 	if (button.getId() == R.id.imageview_currentlocation){
	 		Log.d("########## [DEBUG] ##########","onClick() - Button_GoToCurrentLocation button is clicked");
	 		if(!flagMyLocationOnOff){
	 			startMyLocation();	
	 			flagMyLocationOnOff = true;
	 			button.setBackgroundResource(R.drawable.ic_my_location_clicked);
	 		}
	 		else{
	 			stopMyLocation();
	 			flagMyLocationOnOff = false;
	 			button.setBackgroundResource(R.drawable.ic_my_location_default);
	 		}
	 		
		}	
	 	else if (button.getId() == R.id.imageview_addpin){
	 		Log.d("########## [DEBUG] ##########","onClick() - Button_AddPin button is clicked");
	 		printCurrentLocation();
	 		
	 		if(!addPinOnOff){
	 			drawLineWithDataOverlay();
	 			addPinOnOff = true;
	 		}
	 		else{
	 			addPinOnOff = false;
//	 			pathDataOverlay.setHidden(true); ()
	 			undrawLineWithDataOverlay();
	 			//////////////////////////////////////////////////////// 작업중
	 		}
		}
	 	else if (button.getId() == R.id.imageview_friend){
	 		// 영제 형 요기
	 		if(isFriendsListOpen) {
	 			mapViewLayout.bringToFront();
	 			mapViewLayout.setTranslationX(0);
	 			mapViewLayout.startAnimation(slideLeftAnim);
				
			} else {
				mapViewLayout.startAnimation(slideRightAnim);
				friendslistLayout.setVisibility(View.VISIBLE);
			}
		}
	}
	
	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onStop() {

		stopMyLocation();

		super.onStop();
	}

	@Override
	protected void onDestroy() {

		// save map view state such as map center position and zoom level.
		saveInstanceState();

		super.onDestroy();
	}

	/* Test Functions */

	private void startMyLocation() {

		if (mMyLocationOverlay != null) {
			if (!mOverlayManager.hasOverlay(mMyLocationOverlay)) {
				mOverlayManager.addOverlay(mMyLocationOverlay);
			}

			if (!mMapLocationManager.isMyLocationEnabled()) {
				boolean isMyLocationEnabled = mMapLocationManager.enableMyLocation(true);
				if (!isMyLocationEnabled) {
					Toast.makeText(NMapViewer.this, "Please enable a My Location source in system settings",
						Toast.LENGTH_LONG).show();

					Intent goToSettings = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					startActivity(goToSettings);

					return;
				}
			}
		}
	}
	
	private void stopMyLocation() {
		if (mMyLocationOverlay != null) {
			mMapLocationManager.disableMyLocation();
		}
	}

	
	// 마커 
	private void putPOIdataOverlay(Pin pin) {

		NMapPOIdata poiData = new NMapPOIdata(1, mMapViewerResourceProvider);
		poiData.beginPOIdata(1);
		
//		Log.d("############", "X: "+pin.getxLocation()+" / Y: "+pin.getyLocation());
		NMapPOIitem item = poiData.addPOIitem(pin.getxLocation(), pin.getyLocation(), "NEW", NMapPOIflagType.PIN, pin.getPinId());
		item.setRightAccessory(true, NMapPOIflagType.CLICKABLE_ARROW);
		
		NMapPOIdataOverlay poiDataOverlay = mOverlayManager.createPOIdataOverlay(poiData, null);
		poiDataOverlay.setOnStateChangeListener(onPOIdataStateChangeListener);
		poiDataOverlay.selectPOIitem(0, true);
		poiDataOverlay.showAllPOIdata(0);
	}
	
	// 경로선 그리기
	private void drawLineWithDataOverlay() {
		// set path data points
		NMapPathData pathData = new NMapPathData(pinList.size());
		pathData.initPathData();
		pathDataOverlay = mOverlayManager.createPathDataOverlay(pathData);
		
		for(int i=0; i<pinList.size(); i++){
			if(i == 0){
				pathData.addPathPoint(pinList.get(i).getxLocation(), pinList.get(i).getyLocation(), NMapPathLineStyle.TYPE_SOLID);
			}
			else{
				pathData.addPathPoint(pinList.get(i).getxLocation(), pinList.get(i).getyLocation(), 0);
			}
		}
		pathData.endPathData();
		pathDataOverlay.addPathData(pathData);
		NMapPathLineStyle pathLineStyle = new NMapPathLineStyle(mMapView.getContext());
		pathLineStyle.setPataDataType(NMapPathLineStyle.DATA_TYPE_POLYGON);
		pathLineStyle.setLineColor(0xA04DD2, 0xff);
		pathLineStyle.setFillColor(0xFFFFFF, 0x00);
		pathData.setPathLineStyle(pathLineStyle);
	}
	
	
	// 경로선 그리기
	private void undrawLineWithDataOverlay() {
		// set path data points
		NMapPathData pathData = new NMapPathData(pinList.size());
		pathData.initPathData();
		pathDataOverlay = mOverlayManager.createPathDataOverlay(pathData);
		
		for(int i=0; i<pinList.size(); i++){
			if(i == 0){
				pathData.addPathPoint(pinList.get(i).getxLocation(), pinList.get(i).getyLocation(), NMapPathLineStyle.TYPE_SOLID);
			}
			else{
				pathData.addPathPoint(pinList.get(i).getxLocation(), pinList.get(i).getyLocation(), 0);
			}
		}
		pathData.endPathData();
		pathDataOverlay.addPathData(pathData);
		NMapPathLineStyle pathLineStyle = new NMapPathLineStyle(mMapView.getContext());
		pathLineStyle.setPataDataType(NMapPathLineStyle.DATA_TYPE_POLYGON);
		pathLineStyle.setLineColor(0xA04DD2, 0xff);
		pathLineStyle.setFillColor(0xFFFFFF, 0x00);
		pathData.setPathLineStyle(pathLineStyle);
		pathDataOverlay.setHidden(true); 
	}
	
	
	// 경로선
	private void testPathDataOverlay() {

		// set path data points
		NMapPathData pathData = new NMapPathData(9);

		pathData.initPathData();
		pathData.addPathPoint(127.108099, 37.366034, NMapPathLineStyle.TYPE_SOLID);
		pathData.addPathPoint(127.108088, 37.366043, 0);
		pathData.addPathPoint(127.108079, 37.365619, 0);
		pathData.addPathPoint(127.107458, 37.365608, 0);
		pathData.addPathPoint(127.107232, 37.365608, 0);
		pathData.addPathPoint(127.106904, 37.365624, 0);
		pathData.addPathPoint(127.105933, 37.365621, NMapPathLineStyle.TYPE_DASH);
		pathData.addPathPoint(127.105929, 37.366378, 0);
		pathData.addPathPoint(127.106279, 37.366380, 0);
		pathData.endPathData();

		NMapPathDataOverlay pathDataOverlay = mOverlayManager.createPathDataOverlay(pathData);
		if (pathDataOverlay != null) {

			// add path data with polygon type
			NMapPathData pathData2 = new NMapPathData(4);
			pathData2.initPathData();
			pathData2.addPathPoint(127.106, 37.367, NMapPathLineStyle.TYPE_SOLID);
			pathData2.addPathPoint(127.107, 37.367, 0);
			pathData2.addPathPoint(127.107, 37.368, 0);
			pathData2.addPathPoint(127.106, 37.368, 0);
			pathData2.endPathData();
			pathDataOverlay.addPathData(pathData2);
			// set path line style
			NMapPathLineStyle pathLineStyle = new NMapPathLineStyle(mMapView.getContext());
			pathLineStyle.setPataDataType(NMapPathLineStyle.DATA_TYPE_POLYGON);
			pathLineStyle.setLineColor(0xA04DD2, 0xff);
			pathLineStyle.setFillColor(0xFFFFFF, 0x00);
			pathData2.setPathLineStyle(pathLineStyle);

			// add circle data
			NMapCircleData circleData = new NMapCircleData(1);
			circleData.initCircleData();
			circleData.addCirclePoint(127.1075, 37.3675, 50.0F);
			circleData.endCircleData();
			pathDataOverlay.addCircleData(circleData);
			// set circle style
			NMapCircleStyle circleStyle = new NMapCircleStyle(mMapView.getContext());
			circleStyle.setLineType(NMapPathLineStyle.TYPE_DASH);
			circleStyle.setFillColor(0x000000, 0x00);
			circleData.setCircleStyle(circleStyle);

			// show all path data
			pathDataOverlay.showAllPathData(0);
		}
	}

	
	private void testPathPOIdataOverlay() {

		// set POI data
		NMapPOIdata poiData = new NMapPOIdata(4, mMapViewerResourceProvider, true);
		poiData.beginPOIdata(4); 
		poiData.addPOIitem(349652983, 149297368, "Pizza 124-456", NMapPOIflagType.FROM, null);
		poiData.addPOIitem(349652966, 149296906, null, NMapPOIflagType.NUMBER_BASE + 1, null);
		poiData.addPOIitem(349651062, 149296913, null, NMapPOIflagType.NUMBER_BASE + 999, null);
		poiData.addPOIitem(349651376, 149297750, "Pizza 000-999", NMapPOIflagType.TO, null);
		poiData.endPOIdata();

		// create POI data overlay
		NMapPOIdataOverlay poiDataOverlay = mOverlayManager.createPOIdataOverlay(poiData, null);

		// set event listener to the overlay
		poiDataOverlay.setOnStateChangeListener(onPOIdataStateChangeListener);

	}

	// 마커 
	private void testPOIdataOverlay() {

		// set POI data
		NMapPOIdata poiData = new NMapPOIdata(2, mMapViewerResourceProvider);
		poiData.beginPOIdata(3);
		
		NMapPOIitem item = poiData.addPOIitem(126.978371, 37.5666091, "PIN with CLICKABLE_ARROW", NMapPOIflagType.PIN, 0);
		item.setRightAccessory(true, NMapPOIflagType.CLICKABLE_ARROW);
		
		poiData.addPOIitem(126.978371, 37.5676091,  "PIN", NMapPOIflagType.PIN, 0);
		
		
		
		// create POI data overlay
		NMapPOIdataOverlay poiDataOverlay = mOverlayManager.createPOIdataOverlay(poiData, null);

		// set event listener to the overlay
		poiDataOverlay.setOnStateChangeListener(onPOIdataStateChangeListener);

		// select an item
		poiDataOverlay.selectPOIitem(0, true);

		// show all POI data
		poiDataOverlay.showAllPOIdata(0);
	}

	private void testFloatingPOIdataOverlay() {
		// Markers for POI item
		int marker1 = NMapPOIflagType.PIN;

		// set POI data
		NMapPOIdata poiData = new NMapPOIdata(1, mMapViewerResourceProvider);
		poiData.beginPOIdata(1);
		NMapPOIitem item = poiData.addPOIitem(null, "Touch & Drag to Move", marker1, 0);
		if (item != null) {
			// initialize location to the center of the map view.
			item.setPoint(mMapController.getMapCenter());
			// set floating mode
			item.setFloatingMode(NMapPOIitem.FLOATING_TOUCH | NMapPOIitem.FLOATING_DRAG);
			// show right button on callout
			item.setRightButton(true);

			mFloatingPOIitem = item;
		}
		poiData.endPOIdata();

		// create POI data overlay
		NMapPOIdataOverlay poiDataOverlay = mOverlayManager.createPOIdataOverlay(poiData, null);
		if (poiDataOverlay != null) {
			poiDataOverlay.setOnFloatingItemChangeListener(onPOIdataFloatingItemChangeListener);

			// set event listener to the overlay
			poiDataOverlay.setOnStateChangeListener(onPOIdataStateChangeListener);

			poiDataOverlay.selectPOIitem(0, false);

			mFloatingPOIdataOverlay = poiDataOverlay;
		}
	}

	/* NMapDataProvider Listener */
	private final NMapActivity.OnDataProviderListener onDataProviderListener = new NMapActivity.OnDataProviderListener() {

		@Override
		public void onReverseGeocoderResponse(NMapPlacemark placeMark, NMapError errInfo) {

			if (Constants.DEBUG) {
				Log.i(Constants.LOG_TAG, "onReverseGeocoderResponse: placeMark="
					+ ((placeMark != null) ? placeMark.toString() : null));
			}

			if (errInfo != null) {
				Log.e(Constants.LOG_TAG, "Failed to findPlacemarkAtLocation: error=" + errInfo.toString());

				Toast.makeText(NMapViewer.this, errInfo.toString(), Toast.LENGTH_LONG).show();
				return;
			}

			if (mFloatingPOIitem != null && mFloatingPOIdataOverlay != null) {
				mFloatingPOIdataOverlay.deselectFocusedPOIitem();

				if (placeMark != null) {
					mFloatingPOIitem.setTitle(placeMark.toString());
				}
				mFloatingPOIdataOverlay.selectPOIitemBy(mFloatingPOIitem.getId(), false);
			}
		}

	};

	/* MyLocation Listener */
	private final NMapLocationManager.OnLocationChangeListener onMyLocationChangeListener = new NMapLocationManager.OnLocationChangeListener() {

		@Override
		public boolean onLocationChanged(NMapLocationManager locationManager, NGeoPoint myLocation) {

			if (mMapController != null) {
				mMapController.animateTo(myLocation);
			}

			return true;
		}

		@Override
		public void onLocationUpdateTimeout(NMapLocationManager locationManager) {

			// stop location updating
			//			Runnable runnable = new Runnable() {
			//				public void run() {										
			//					stopMyLocation();
			//				}
			//			};
			//			runnable.run();	

			Toast.makeText(NMapViewer.this, "Your current location is temporarily unavailable.", Toast.LENGTH_LONG).show();
		}

		@Override
		public void onLocationUnavailableArea(NMapLocationManager locationManager, NGeoPoint myLocation) {

			Toast.makeText(NMapViewer.this, "Your current location is unavailable area.", Toast.LENGTH_LONG).show();

			stopMyLocation();
		}

	};

	/* MapView State Change Listener*/
	private final NMapView.OnMapStateChangeListener onMapViewStateChangeListener = new NMapView.OnMapStateChangeListener() {

		@Override
		public void onMapInitHandler(NMapView mapView, NMapError errorInfo) {

			if (errorInfo == null) { // success
				// restore map view state such as map center position and zoom level.
				restoreInstanceState();

			} else { // fail
				Log.e(Constants.LOG_TAG, "onFailedToInitializeWithError: " + errorInfo.toString());

				Toast.makeText(NMapViewer.this, errorInfo.toString(), Toast.LENGTH_LONG).show();
			}
		}

		@Override
		public void onAnimationStateChange(NMapView mapView, int animType, int animState) {
			if (Constants.DEBUG) {
				Log.i(Constants.LOG_TAG, "onAnimationStateChange: animType=" + animType + ", animState=" + animState);
			}
		}

		@Override
		public void onMapCenterChange(NMapView mapView, NGeoPoint center) {
			if (Constants.DEBUG) {
				Log.i(Constants.LOG_TAG, "onMapCenterChange: center=" + center.toString());
			}
		}

		@Override
		public void onZoomLevelChange(NMapView mapView, int level) {
			if (Constants.DEBUG) {
				Log.i(Constants.LOG_TAG, "onZoomLevelChange: level=" + level);
			}
		}

		@Override
		public void onMapCenterChangeFine(NMapView mapView) {

		}
	};

	private final NMapView.OnMapViewTouchEventListener onMapViewTouchEventListener = new NMapView.OnMapViewTouchEventListener() {

		@Override
		public void onLongPress(NMapView mapView, MotionEvent ev) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onLongPressCanceled(NMapView mapView) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSingleTapUp(NMapView mapView, MotionEvent ev) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onTouchDown(NMapView mapView, MotionEvent ev) {

		}

		@Override
		public void onScroll(NMapView mapView, MotionEvent e1, MotionEvent e2) {
		}

		@Override
		public void onTouchUp(NMapView mapView, MotionEvent ev) {
			// TODO Auto-generated method stub

		}

	};

	private final NMapView.OnMapViewDelegate onMapViewTouchDelegate = new NMapView.OnMapViewDelegate() {

		@Override
		public boolean isLocationTracking() {
			if (mMapLocationManager != null) {
				if (mMapLocationManager.isMyLocationEnabled()) {
					return mMapLocationManager.isMyLocationFixed();
				}
			}
			return false;
		}

	};

	/* POI data State Change Listener*/
	private final NMapPOIdataOverlay.OnStateChangeListener onPOIdataStateChangeListener = new NMapPOIdataOverlay.OnStateChangeListener() {

		@Override
		public void onCalloutClick(NMapPOIdataOverlay poiDataOverlay, NMapPOIitem item) {
			if (Constants.DEBUG) {
				Log.i(Constants.LOG_TAG, "onCalloutClick: title=" + item.getTitle());
			}

			// [[TEMP]] handle a click event of the callout
			Toast.makeText(NMapViewer.this, "onCalloutClick: " + item.getId(), Toast.LENGTH_LONG).show();
			Intent intent = new Intent(NMapViewer.this, DetailedPinActivity.class);
			intent.putExtra("pinId", item.getId());
			startActivity(intent);
		}

		@Override
		public void onFocusChanged(NMapPOIdataOverlay poiDataOverlay, NMapPOIitem item) {
			if (Constants.DEBUG) {
				if (item != null) {
					Log.i(Constants.LOG_TAG, "onFocusChanged: " + item.toString());
				} else {
					Log.i(Constants.LOG_TAG, "onFocusChanged: ");
				}
			}
		}
	};

	private final NMapPOIdataOverlay.OnFloatingItemChangeListener onPOIdataFloatingItemChangeListener = new NMapPOIdataOverlay.OnFloatingItemChangeListener() {

		@Override
		public void onPointChanged(NMapPOIdataOverlay poiDataOverlay, NMapPOIitem item) {
			NGeoPoint point = item.getPoint();

			if (Constants.DEBUG) {
				Log.i(Constants.LOG_TAG, "onPointChanged: point=" + point.toString());
			}

			findPlacemarkAtLocation(point.longitude, point.latitude);

			item.setTitle(null);

		}
	};

	private final NMapOverlayManager.OnCalloutOverlayListener onCalloutOverlayListener = new NMapOverlayManager.OnCalloutOverlayListener() {

		@Override
		public NMapCalloutOverlay onCreateCalloutOverlay(NMapOverlay itemOverlay, NMapOverlayItem overlayItem,
			Rect itemBounds) {

			// handle overlapped items
			if (itemOverlay instanceof NMapPOIdataOverlay) {
				NMapPOIdataOverlay poiDataOverlay = (NMapPOIdataOverlay)itemOverlay;

				// check if it is selected by touch event
				if (!poiDataOverlay.isFocusedBySelectItem()) {
					int countOfOverlappedItems = 1;

					NMapPOIdata poiData = poiDataOverlay.getPOIdata();
					for (int i = 0; i < poiData.count(); i++) {
						NMapPOIitem poiItem = poiData.getPOIitem(i);

						// skip selected item
						if (poiItem == overlayItem) {
							continue;
						}

						// check if overlapped or not
						if (Rect.intersects(poiItem.getBoundsInScreen(), overlayItem.getBoundsInScreen())) {
							countOfOverlappedItems++;
						}
					}

					if (countOfOverlappedItems > 1) {
						String text = countOfOverlappedItems + " overlapped items for " + overlayItem.getTitle();
						Toast.makeText(NMapViewer.this, text, Toast.LENGTH_LONG).show();
						return null;
					}
				}
			}

			// use custom old callout overlay
			if (overlayItem instanceof NMapPOIitem) {
				NMapPOIitem poiItem = (NMapPOIitem)overlayItem;

				if (poiItem.showRightButton()) {
					return new NMapCalloutCustomOldOverlay(itemOverlay, overlayItem, itemBounds,
						mMapViewerResourceProvider);
				}
			}

			// use custom callout overlay
			return new NMapCalloutCustomOverlay(itemOverlay, overlayItem, itemBounds, mMapViewerResourceProvider);

			// set basic callout overlay
			//return new NMapCalloutBasicOverlay(itemOverlay, overlayItem, itemBounds);			
		}

	};

	private final NMapOverlayManager.OnCalloutOverlayViewListener onCalloutOverlayViewListener = new NMapOverlayManager.OnCalloutOverlayViewListener() {

		@Override
		public View onCreateCalloutOverlayView(NMapOverlay itemOverlay, NMapOverlayItem overlayItem, Rect itemBounds) {

			if (overlayItem != null) {
				// [TEST] 말풍선 오버레이를 뷰로 설정함
				String title = overlayItem.getTitle();
				if (title != null && title.length() > 5) {
					return new NMapCalloutCustomOverlayView(NMapViewer.this, itemOverlay, overlayItem, itemBounds);
				}
			}

			// null을 반환하면 말풍선 오버레이를 표시하지 않음
			return null;
		}

	};

	/* Local Functions */

	private void restoreInstanceState() {
		mPreferences = getPreferences(MODE_PRIVATE);

		int longitudeE6 = mPreferences.getInt(Constants.KEY_CENTER_LONGITUDE, Constants.NMAP_LOCATION_DEFAULT.getLongitudeE6());
		int latitudeE6 = mPreferences.getInt(Constants.KEY_CENTER_LATITUDE, Constants.NMAP_LOCATION_DEFAULT.getLatitudeE6());
		int level = mPreferences.getInt(Constants.KEY_ZOOM_LEVEL, Constants.NMAP_ZOOMLEVEL_DEFAULT);
		int viewMode = mPreferences.getInt(Constants.KEY_VIEW_MODE, Constants.NMAP_VIEW_MODE_DEFAULT);
		boolean trafficMode = mPreferences.getBoolean(Constants.KEY_TRAFFIC_MODE, Constants.NMAP_TRAFFIC_MODE_DEFAULT);
		boolean bicycleMode = mPreferences.getBoolean(Constants.KEY_BICYCLE_MODE, Constants.NMAP_BICYCLE_MODE_DEFAULT);

		mMapController.setMapViewMode(viewMode);
		mMapController.setMapViewTrafficMode(trafficMode);
		mMapController.setMapViewBicycleMode(bicycleMode);
		mMapController.setMapCenter(new NGeoPoint(longitudeE6, latitudeE6), level);
	}

	private void saveInstanceState() {
		if (mPreferences == null) {
			return;
		}

		NGeoPoint center = mMapController.getMapCenter();
		int level = mMapController.getZoomLevel();
		int viewMode = mMapController.getMapViewMode();
		boolean trafficMode = mMapController.getMapViewTrafficMode();
		boolean bicycleMode = mMapController.getMapViewBicycleMode();

		SharedPreferences.Editor edit = mPreferences.edit();

		edit.putInt(Constants.KEY_CENTER_LONGITUDE, center.getLongitudeE6());
		edit.putInt(Constants.KEY_CENTER_LATITUDE, center.getLatitudeE6());
		edit.putInt(Constants.KEY_ZOOM_LEVEL, level);
		edit.putInt(Constants.KEY_VIEW_MODE, viewMode);
		edit.putBoolean(Constants.KEY_TRAFFIC_MODE, trafficMode);
		edit.putBoolean(Constants.KEY_BICYCLE_MODE, bicycleMode);

		edit.commit();

	}


	/**
	 * Invoked during init to give the Activity a chance to set up its Menu.
	 * 
	 * @param menu the Menu to which entries may be added
	 * @return true
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		MenuItem menuItem = null;
		SubMenu subMenu = null;

		menuItem = menu.add(Menu.NONE, Constants.MENU_ITEM_CLEAR_MAP, Menu.CATEGORY_SECONDARY, "초기화");
		menuItem.setAlphabeticShortcut('c');
		menuItem.setIcon(android.R.drawable.ic_menu_revert);

		subMenu = menu.addSubMenu(Menu.NONE, Constants.MENU_ITEM_MAP_MODE, Menu.CATEGORY_SECONDARY, "지도보기");
		subMenu.setIcon(android.R.drawable.ic_menu_mapmode);

		menuItem = subMenu.add(0, Constants.MENU_ITEM_MAP_MODE_SUB_VECTOR, Menu.NONE, "일반지도");
		menuItem.setAlphabeticShortcut('m');
		menuItem.setCheckable(true);
		menuItem.setChecked(false);

		menuItem = subMenu.add(0, Constants.MENU_ITEM_MAP_MODE_SUB_SATELLITE, Menu.NONE, "위성지도");
		menuItem.setAlphabeticShortcut('s');
		menuItem.setCheckable(true);
		menuItem.setChecked(false);

		menuItem = subMenu.add(0, Constants.MENU_ITEM_MAP_MODE_SUB_TRAFFIC, Menu.NONE, "실시간교통");
		menuItem.setAlphabeticShortcut('t');
		menuItem.setCheckable(true);
		menuItem.setChecked(false);

		menuItem = subMenu.add(0, Constants.MENU_ITEM_MAP_MODE_SUB_BICYCLE, Menu.NONE, "자전거지도");
		menuItem.setAlphabeticShortcut('b');
		menuItem.setCheckable(true);
		menuItem.setChecked(false);

		menuItem = menu.add(0, Constants.MENU_ITEM_ZOOM_CONTROLS, Menu.CATEGORY_SECONDARY, "Zoom Controls");
		menuItem.setAlphabeticShortcut('z');
		menuItem.setIcon(android.R.drawable.ic_menu_zoom);

		menuItem = menu.add(0, Constants.MENU_ITEM_MY_LOCATION, Menu.CATEGORY_SECONDARY, "내위치");
		menuItem.setAlphabeticShortcut('l');
		menuItem.setIcon(android.R.drawable.ic_menu_mylocation);

		subMenu = menu.addSubMenu(Menu.NONE, Constants.MENU_ITEM_TEST_MODE, Menu.CATEGORY_SECONDARY, "테스트");
		subMenu.setIcon(android.R.drawable.ic_menu_more);

		menuItem = subMenu.add(0, Constants.MENU_ITEM_TEST_POI_DATA, Menu.NONE, "마커 표시");
		menuItem.setAlphabeticShortcut('p');

		menuItem = subMenu.add(0, Constants.MENU_ITEM_TEST_PATH_DATA, Menu.NONE, "경로선 표시");
		menuItem.setAlphabeticShortcut('t');

		menuItem = subMenu.add(0, Constants.MENU_ITEM_TEST_FLOATING_DATA, Menu.NONE, "직접 지정");
		menuItem.setAlphabeticShortcut('f');

		menuItem = subMenu.add(0, Constants.MENU_ITEM_TEST_AUTO_ROTATE, Menu.NONE, "지도 회전");
		menuItem.setAlphabeticShortcut('a');

		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu pMenu) {
		super.onPrepareOptionsMenu(pMenu);

		int viewMode = mMapController.getMapViewMode();
		boolean isTraffic = mMapController.getMapViewTrafficMode();
		boolean isBicycle = mMapController.getMapViewBicycleMode();

		pMenu.findItem(Constants.MENU_ITEM_CLEAR_MAP).setEnabled(
			(viewMode != NMapView.VIEW_MODE_VECTOR) || isTraffic || mOverlayManager.sizeofOverlays() > 0);
		pMenu.findItem(Constants.MENU_ITEM_MAP_MODE_SUB_VECTOR).setChecked(viewMode == NMapView.VIEW_MODE_VECTOR);
		pMenu.findItem(Constants.MENU_ITEM_MAP_MODE_SUB_SATELLITE).setChecked(viewMode == NMapView.VIEW_MODE_HYBRID);
		pMenu.findItem(Constants.MENU_ITEM_MAP_MODE_SUB_TRAFFIC).setChecked(isTraffic);
		pMenu.findItem(Constants.MENU_ITEM_MAP_MODE_SUB_BICYCLE).setChecked(isBicycle);

		if (mMyLocationOverlay == null) {
			pMenu.findItem(Constants.MENU_ITEM_MY_LOCATION).setEnabled(false);
		}

		return true;
	}

	/**
	 * Invoked when the user selects an item from the Menu.
	 * 
	 * @param item the Menu entry which was selected
	 * @return true if the Menu item was legit (and we consumed it), false
	 *         otherwise
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
			case Constants.MENU_ITEM_CLEAR_MAP:
				if (mMyLocationOverlay != null) {
					stopMyLocation();
					mOverlayManager.removeOverlay(mMyLocationOverlay);
				}

				mMapController.setMapViewMode(NMapView.VIEW_MODE_VECTOR);
				mMapController.setMapViewTrafficMode(false);
				mMapController.setMapViewBicycleMode(false);

				mOverlayManager.clearOverlays();

				return true;

			case Constants.MENU_ITEM_MAP_MODE_SUB_VECTOR:
				mMapController.setMapViewMode(NMapView.VIEW_MODE_VECTOR);
				return true;

			case Constants.MENU_ITEM_MAP_MODE_SUB_SATELLITE:
				mMapController.setMapViewMode(NMapView.VIEW_MODE_HYBRID);
				return true;

			case Constants.MENU_ITEM_MAP_MODE_SUB_TRAFFIC:
				mMapController.setMapViewTrafficMode(!mMapController.getMapViewTrafficMode());
				return true;

			case Constants.MENU_ITEM_MAP_MODE_SUB_BICYCLE:
				mMapController.setMapViewBicycleMode(!mMapController.getMapViewBicycleMode());
				return true;

			case Constants.MENU_ITEM_ZOOM_CONTROLS:
				mMapView.displayZoomControls(true);
				return true;

			case Constants.MENU_ITEM_MY_LOCATION:
				startMyLocation();
				return true;

			case Constants.MENU_ITEM_TEST_POI_DATA:
				mOverlayManager.clearOverlays();

				// add POI data overlay
				testPOIdataOverlay();
				return true;

			case Constants.MENU_ITEM_TEST_PATH_DATA:
				mOverlayManager.clearOverlays();

				// add path data overlay
				testPathDataOverlay();

				// add path POI data overlay
				testPathPOIdataOverlay();
				return true;

			case Constants.MENU_ITEM_TEST_FLOATING_DATA:
				mOverlayManager.clearOverlays();
				testFloatingPOIdataOverlay();
				return true;

			case Constants.MENU_ITEM_TEST_AUTO_ROTATE:
				if (mMapView.isAutoRotateEnabled()) {
					mMapView.setAutoRotateEnabled(false, false);

					mMapContainerView.requestLayout();

					mHnadler.removeCallbacks(mTestAutoRotation);
				} else {

					mMapView.setAutoRotateEnabled(true, false);

					mMapView.setRotateAngle(30);
					mHnadler.postDelayed(mTestAutoRotation, AUTO_ROTATE_INTERVAL);

					mMapContainerView.requestLayout();
				}
				return true;
		}

		return super.onOptionsItemSelected(item);
	}

	private static final long AUTO_ROTATE_INTERVAL = 2000;
	private final Handler mHnadler = new Handler();
	private final Runnable mTestAutoRotation = new Runnable() {
		@Override
		public void run() {
//        	if (mMapView.isAutoRotateEnabled()) {
//    			float degree = (float)Math.random()*360;
//    			
//    			degree = mMapView.getRoateAngle() + 30;
//
//    			mMapView.setRotateAngle(degree);	
//            	
//            	mHnadler.postDelayed(mTestAutoRotation, AUTO_ROTATE_INTERVAL);        		
//        	}
		}
	};

	/** 
	 * Container view class to rotate map view.
	 */
	private class MapContainerView extends ViewGroup {

		public MapContainerView(Context context) {
			super(context);
		}

		@Override
		protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
			final int width = getWidth();
			final int height = getHeight();
			final int count = getChildCount();
			for (int i = 0; i < count; i++) {
				final View view = getChildAt(i);
				final int childWidth = view.getMeasuredWidth();
				final int childHeight = view.getMeasuredHeight();
				final int childLeft = (width - childWidth) / 2;
				final int childTop = (height - childHeight) / 2;
				view.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
			}

			if (changed) {
				mOverlayManager.onSizeChanged(width, height);
			}
		}

		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			int w = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
			int h = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
			int sizeSpecWidth = widthMeasureSpec;
			int sizeSpecHeight = heightMeasureSpec;

			final int count = getChildCount();
			for (int i = 0; i < count; i++) {
				final View view = getChildAt(i);

				if (view instanceof NMapView) {
					if (mMapView.isAutoRotateEnabled()) {
						int diag = (((int)(Math.sqrt(w * w + h * h)) + 1) / 2 * 2);
						sizeSpecWidth = MeasureSpec.makeMeasureSpec(diag, MeasureSpec.EXACTLY);
						sizeSpecHeight = sizeSpecWidth;
					}
				}

				view.measure(sizeSpecWidth, sizeSpecHeight);
			}
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}
	
	
}
