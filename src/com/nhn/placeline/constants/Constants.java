package com.nhn.placeline.constants;

import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;

public class Constants {

	public static final String LOG_TAG = "NMapViewer";
	public static final boolean DEBUG = false;
	public static final String API_KEY = "9a3d0bd5abf2343373c74643b37b2760";
	
	public static final int BUTTON_ID_CURRENT_LOCATION = 1;
	public static final int BUTTON_ID_ADD_PIN = 2;
	public static final int BUTTON_ID_FRIENDS_LIST = 3;

	public static final NGeoPoint NMAP_LOCATION_DEFAULT = new NGeoPoint(126.978371, 37.5666091);
	public static final int NMAP_ZOOMLEVEL_DEFAULT = 11;
	public static final int NMAP_VIEW_MODE_DEFAULT = NMapView.VIEW_MODE_VECTOR;
	public static final boolean NMAP_TRAFFIC_MODE_DEFAULT = false;
	public static final boolean NMAP_BICYCLE_MODE_DEFAULT = false;

	public static final String KEY_ZOOM_LEVEL = "NMapViewer.zoomLevel";
	public static final String KEY_CENTER_LONGITUDE = "NMapViewer.centerLongitudeE6";
	public static final String KEY_CENTER_LATITUDE = "NMapViewer.centerLatitudeE6";
	public static final String KEY_VIEW_MODE = "NMapViewer.viewMode";
	public static final String KEY_TRAFFIC_MODE = "NMapViewer.trafficMode";
	public static final String KEY_BICYCLE_MODE = "NMapViewer.bicycleMode";

}