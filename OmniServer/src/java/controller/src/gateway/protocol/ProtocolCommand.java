package gateway.protocol;

import java.io.Serializable;

public enum ProtocolCommand implements Serializable{
    InitializeRemoteReq,
    Nack,
    RebootReq,
    RebootRsp,
    InfoReq,
    InfoRsp,
    RegisterDeviceReq,
    RegisterDeviceRsp,
    StoreDeviceDataReq,
    StoreDeviceDataRsp,
    CurrentStatusReq,
    CurrentStatusRsp,
    SetDeviceStatusReq,
    SetDeviceStatusRsp,
    GetDeviceListReq,
    GetDeviceListRsp
}
