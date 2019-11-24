package id.campusin.tanyakampus.helper;

import static id.campusin.tanyakampus.utils.config.Configuration.BASE_URL_API;

public class ApiHelper {

    public static ApiInterfaceService process(){
        return RetrofitUtils.getClient(BASE_URL_API).create(ApiInterfaceService.class);
    }
}
