package com.ruoyi.system.common;

import com.ruoyi.system.domain.Offer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author QC
 * @create 2020-08-21 10:23
 */
public class Const {

    public interface WarehouseRecordStatus{
        int STORAGE_IN = 1;
        int STORAGE_OUT = 2;
        int  STORAGE_QUIT=3 ;
        int STORAGE_IN_HC = 4;
        int STORAGE_OUT_HC = 5;
        int  STORAGE_QUIT_HC=6 ;
    }

    public interface BomStatus{
        int BOM_SCRAP = 1;

    }


    public interface OfferData{
       Map<String,Offer>  map=new HashMap<>();
    }
    public enum Storageinbilldelstatus{
        NOHONGCHONG(1,"未红冲"),
        HONGCHONG(2,"以红冲");

        private int code;
        private String  value;
        Storageinbilldelstatus(int code,String value){
            this.code = code;
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public enum Storagestatus{
        BEFOREHAND(1,"预入库"),
        OFFICIAL(2,"正式入库");

        Storagestatus(int code,String value){
            this.code = code;
            this.value = value;
        }

        private int code;
        private String  value;

        public int getCode() {
            return code;
        }


        public String getValue() {
            return value;
        }


    }

}
