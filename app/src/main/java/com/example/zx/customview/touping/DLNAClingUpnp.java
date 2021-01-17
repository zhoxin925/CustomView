package com.example.zx.customview.touping;

import java.util.ArrayList;

/**
 * Created by zx on 2021/1/17 19:24
 * Describe:
 */
class DLNAClingUpnp {

    /*
     * ...
     * //添加服务设备
     * sUpnpService.getRegistry().addDevice(sMediaServer.getDevice());
     * //添加对UPNP服务的监听局域网内有哪些支持DLNA的设备
     * sUpnpService.getRegistry().addListener(sRegistryListener);
     * //开始发现设备
     * sUpnpService.getControlPoint().search();
     * ...
     *
     * sControlPoint.execute(new SetAVTransportURI(avtService, url,metadata) {
            @Override
            public void success(ActionInvocation invocation) {
                super.success(invocation);
            }

            @Override
            public void failure(ActionInvocation invocation, UpnpResponse operation, String defaultMsg) {
            }
        });
     *
     */

    /*private static BrowseRegistryListener sRegistryListener = new BrowseRegistryListener();

    public class BrowseRegistryListener extends DefaultRegistryListener {

        private ArrayList<Device> mDeviceList;

        public  BrowseRegistryListener(ArrayList<Device> deviceList){
            mDeviceList = deviceList;
        }

        @Override
        public void deviceAdded(Registry registry, Device device) {
            super.deviceAdded(registry, device);
            mDeviceList.add(device);
        }

        @Override
        public void deviceRemoved(Registry registry, Device device) {
            super.deviceRemoved(registry, device);
            mDeviceList.remove(device);
        }
    }*/
}
