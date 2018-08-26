package com.twitter.account;

public class AccountSettingsJsonPath
{
    public static final String name="time_zone.name";
    public static final String utc_offset="time_zone.utc_offset";
    public static final String tzinfo_name="time_zone.tzinfo_name";
	
    public static final String Protected="protected";
    public static final String screen_name="screen_name";
    public static final String always_use_https="always_use_https";
    public static final String use_cookie_personalization="use_cookie_personalization";

    public static final String enabled="sleep_time.enabled";
    public static final String end_time="sleep_time.end_time";
    public static final String start_time="sleep_time.start_time";
    
    public static final String geo_enabled="geo_enabled";
    public static final String language="language";
    public static final String discoverable_by_email="discoverable_by_email";
    
    public static final String discoverable_by_mobile_phone="discoverable_by_mobile_phone";
    public static final String display_sensitive_media="display_sensitive_media";
    public static final String allow_contributor_request="allow_contributor_request";
    
    public static final String allow_dms_from="allow_dms_from";
    public static final String allow_dm_groups_from="allow_dm_groups_from";
    public static final String translator_type="translator_type";
    
    public static final String location_name="trend_location[0].name";
    public static final String countryCode="trend_location[0].countryCode";
    public static final String url="trend_location[0].url";
    public static final String woeid="trend_location[0].woeid";
    
    public static final String placeType_name="trend_location[0].placeType.name";
    public static final String code="trend_location[0].placeType.code";
    public static final String parentid="trend_location[0].parentid";
    public static final String country="trend_location[0].country";
   
}
