package com.twitter.application;

public class RateLimitStatusJsonPath 
{
   public static final String access_token="rate_limit_context.access_token";
   public static final String users_report_spam_limit="resources.users.'/users/profile_banner'.limit";
   public static final String users_report_spam_remaining="resources.users.'/users/profile_banner'.remaining";
   public static final String users_report_spam_reset="resources.users.'/users/profile_banner'.reset";
   
   
   public static final String users_show_id_limit="resources.users.'/users/show/:id'.limit";
   public static final String users_show_id_remaining="resources.users.'/users/show/:id'.remaining";
   public static final String users_show_id_reset="resources.users.'/users/show/:id'.reset";

   
   
   
   
}
