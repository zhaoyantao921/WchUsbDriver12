package com.wch.wchusbdriver;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zyt on 15/10/27.
 */
public class HttpAsyncTask extends AsyncTask<String,String,String >{

   private TextView textView;
    public HttpAsyncTask(TextView textView){
        super();
        this.textView=textView;
    }


    @Override
    protected String doInBackground(String... params) {
        String str="网络异常。。。。";
        URL url;
        String URL_PATH=params[0];//处理参数的jsp页面地址；

        Map<String ,String > mapParams=new HashMap<String, String>();

        mapParams.put("nickname",params[1]);
        mapParams.put("content", params[2]);
        //获取byte数组
        byte[] data=getRequestData(mapParams,"utf-8").toString().getBytes();
//开始通信；

        HttpURLConnection connection=null;
        try{
            url=new URL(URL_PATH);
            connection=(HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setUseCaches(false);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //获取输出流；
            OutputStream out=connection.getOutputStream();
            out.write(data);
            //获取响应码；
            int response =connection.getResponseCode();
            if(response==HttpURLConnection.HTTP_OK){
                InputStream in=connection.getInputStream();
                str=dealResponseResult(in);//处理响应结果函数；
            }else{
                str="HTTP异常";
            }

        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(connection!=null){
                connection.disconnect();
            }
        }


        return str;
    }

    private String dealResponseResult(InputStream in) {
        String str;
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        byte[] data=new byte[1024];
        int len;
        try{
            while ((len=in.read(data))!=-1){
                byteArrayOutputStream.write(data,0,len);
            }

        }catch(IOException e){
            e.printStackTrace();
        }
        str=new String(byteArrayOutputStream.toByteArray());
        return str;

    }

    private  String getRequestData(Map <String ,String >params, String encoding) {
        //处理Map数据称byte数组的方法；
        StringBuilder stringBuilder=new StringBuilder();
        try {
            for(Map.Entry<String,String >entry:params.entrySet()){
                stringBuilder.append(entry.getKey())
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(),encoding))
                        .append("&");
            }
            stringBuilder.deleteCharAt(stringBuilder.length()-1);

        }catch (Exception e){
            e.printStackTrace();
        }
        return String.valueOf(stringBuilder);


    }

    @Override
    protected void onPostExecute(String s) {
        textView.setText("后台上传结果："+ s);
        super.onPostExecute(s);
    }

    @Override
    protected void onPreExecute() {
        textView.setText("后台上传开始...");
        super.onPreExecute();
    }
}
