package com.reactnativejitsimeet;

import android.util.Log;
import java.net.URL;
import java.net.MalformedURLException;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableArray;

@ReactModule(name = RNJitsiMeetModule.MODULE_NAME)
public class RNJitsiMeetModule extends ReactContextBaseJavaModule {
    public static final String MODULE_NAME = "RNJitsiMeetModule";
    private IRNJitsiMeetViewReference mJitsiMeetViewReference;

    public RNJitsiMeetModule(ReactApplicationContext reactContext, IRNJitsiMeetViewReference jitsiMeetViewReference) {
        super(reactContext);
        mJitsiMeetViewReference = jitsiMeetViewReference;
    }

    @Override
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void initialize() {
        Log.d("JitsiMeet", "Initialize is deprecated in v2");
    }

    @ReactMethod
    public void call(String url, ReadableMap userInfo, ReadableArray disableFeatures) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mJitsiMeetViewReference.getJitsiMeetView() != null) {
                    RNJitsiMeetUserInfo _userInfo = new RNJitsiMeetUserInfo();
                    if (userInfo != null) {
                        if (userInfo.hasKey("displayName")) {
                            _userInfo.setDisplayName(userInfo.getString("displayName"));
                        }
                        if (userInfo.hasKey("email")) {
                            _userInfo.setEmail(userInfo.getString("email"));
                        }
                        if (userInfo.hasKey("avatar")) {
                            String avatarURL = userInfo.getString("avatar");
                            try {
                                _userInfo.setAvatar(new URL(avatarURL));
                            } catch (MalformedURLException e) {
                            }
                        }
                    }
                    // Set initial options of call
                    RNJitsiMeetConferenceOptions.Builder options = new RNJitsiMeetConferenceOptions.Builder()
                            .setRoom(url)
                            .setAudioOnly(false)
                            .setWelcomePageEnabled(false)
                            .setUserInfo(_userInfo);

                    // Iterate over array of flags passed from RN
                    for (int i = 0; i < disableFeatures.size(); i++) {
                        options.setFeatureFlag(disableFeatures.getString(i), false);
                    }

                    // Build options for call
                    RNJitsiMeetConferenceOptions finalOptions = options.build();

                    mJitsiMeetViewReference.getJitsiMeetView().join(finalOptions);
                }
            }
        });
    }

    @ReactMethod
    public void audioCall(String url, ReadableMap userInfo, ReadableArray disableFeatures) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mJitsiMeetViewReference.getJitsiMeetView() != null) {
                    RNJitsiMeetUserInfo _userInfo = new RNJitsiMeetUserInfo();
                    if (userInfo != null) {
                        if (userInfo.hasKey("displayName")) {
                            _userInfo.setDisplayName(userInfo.getString("displayName"));
                        }
                        if (userInfo.hasKey("email")) {
                            _userInfo.setEmail(userInfo.getString("email"));
                        }
                        if (userInfo.hasKey("avatar")) {
                            String avatarURL = userInfo.getString("avatar");
                            try {
                                _userInfo.setAvatar(new URL(avatarURL));
                            } catch (MalformedURLException e) {
                            }
                        }
                    }
                    // Set initial options of call
                    RNJitsiMeetConferenceOptions.Builder options = new RNJitsiMeetConferenceOptions.Builder()
                            .setRoom(url)
                            .setAudioOnly(true)
                            .setWelcomePageEnabled(false)
                            .setUserInfo(_userInfo);

                    // Iterate over array of flags passed from RN
                    for (int i = 0; i < disableFeatures.size(); i++) {
                        options.setFeatureFlag(disableFeatures.getString(i), false);
                    }

                    // Build options for call
                    RNJitsiMeetConferenceOptions finalOptions = options.build();

                    mJitsiMeetViewReference.getJitsiMeetView().join(finalOptions);
                }
            }
        });
    }

    @ReactMethod
    public void endCall() {
        UiThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mJitsiMeetViewReference.getJitsiMeetView() != null) {
                    mJitsiMeetViewReference.getJitsiMeetView().leave();
                }
            }
        });
    }
}
