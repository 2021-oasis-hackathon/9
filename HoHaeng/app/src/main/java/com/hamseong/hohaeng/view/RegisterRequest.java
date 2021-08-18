package com.hamseong.hohaeng.view;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://222.105.251.88:15100/register.php";
    private Map<String, String> map;


    public RegisterRequest(String name, String id, String pw, String address, int age, String sex, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("name",name);
        map.put("id", id);
        map.put("pw", pw);
        map.put("address", address );
        map.put("age", age+"" );
        map.put("sex", sex );


    }

    @Override
    protected Map<String, String> getParams() throws  AuthFailureError {
        return map;
    }
}