/**
 * @providesModule JitsiMeet
 */

import { NativeModules, requireNativeComponent } from 'react-native';

export const JitsiMeetView = requireNativeComponent('RNJitsiMeetView');
export const JitsiMeetModule = NativeModules.RNJitsiMeetView;
const call = JitsiMeetModule.call;
const audioCall = JitsiMeetModule.audioCall;
JitsiMeetModule.call = (url, userInfo, disableFeatures, videoMuted = false) => {
  userInfo = userInfo || {};
  call(url, userInfo, disableFeatures, videoMuted);
}
JitsiMeetModule.audioCall = (url, userInfo, disableFeatures) => {
  userInfo = userInfo || {};
  audioCall(url, userInfo, disableFeatures);
}
export default JitsiMeetModule;


