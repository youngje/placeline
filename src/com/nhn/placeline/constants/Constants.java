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

	//GPS Location (신라호텔) :  126.4085 / 33.2480 

	public static final NGeoPoint NMAP_LOCATION_DEFAULT = new NGeoPoint(126.4085, 33.2480);
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
	
	public static final int GROUPMAP_IMAGE_ID = 100;

	public static boolean USE_XML_LAYOUT = false;
	
	public static final String ADD_GROUP = "AddGroup";
	
	
	/* Menus */
	public static final int MENU_ITEM_CLEAR_MAP = 10;
	public static final int MENU_ITEM_MAP_MODE = 20;
	public static final int MENU_ITEM_MAP_MODE_SUB_VECTOR = MENU_ITEM_MAP_MODE + 1;
	public static final int MENU_ITEM_MAP_MODE_SUB_SATELLITE = MENU_ITEM_MAP_MODE + 2;
	public static final int MENU_ITEM_MAP_MODE_SUB_TRAFFIC = MENU_ITEM_MAP_MODE + 3;
	public static final int MENU_ITEM_MAP_MODE_SUB_BICYCLE = MENU_ITEM_MAP_MODE + 4;
	public static final int MENU_ITEM_ZOOM_CONTROLS = 30;
	public static final int MENU_ITEM_MY_LOCATION = 40;

	public static final int MENU_ITEM_TEST_MODE = 50;
	public static final int MENU_ITEM_TEST_POI_DATA = MENU_ITEM_TEST_MODE + 1;
	public static final int MENU_ITEM_TEST_PATH_DATA = MENU_ITEM_TEST_MODE + 2;
	public static final int MENU_ITEM_TEST_FLOATING_DATA = MENU_ITEM_TEST_MODE + 3;
	public static final int MENU_ITEM_TEST_AUTO_ROTATE = MENU_ITEM_TEST_MODE + 4;
}