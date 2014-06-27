package com.chenlu.activity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView wenduTextView;
	private SensorManager mSensorManager;
	 private int BatteryN;       //Ŀǰ����  
	    private int BatteryV;       //��ص�ѹ  
	    private double BatteryT;        //����¶�  
	    private String BatteryStatus;   //���״̬  
	    private String BatteryTemp;     //���ʹ�����  
	  
	    public TextView TV;  
	      
	    @Override  
	    public void onCreate(Bundle savedInstanceState)   
	    {  
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.activity_main);  
	  
	        // ע��һ��ϵͳ BroadcastReceiver����Ϊ���ʵ�ؼ���֮���������ֱ����AndroidManifest.xml��ע��  
	        registerReceiver(mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));  
	          
	        TV = (TextView)findViewById(R.id.wendu);  
	          
	    }  
	      
	    /* �����㲥������ */  
	    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver()   
	    {  
	        public void onReceive(Context context, Intent intent)   
	        {  
	            String action = intent.getAction();  
	            /* 
	             * �����׽����action��ACTION_BATTERY_CHANGED�� ������onBatteryInfoReceiver() 
	             */  
	            if (Intent.ACTION_BATTERY_CHANGED.equals(action))   
	            {  
	                BatteryN = intent.getIntExtra("level", 0);    //Ŀǰ����  
	                BatteryV = intent.getIntExtra("voltage", 0);  //��ص�ѹ  
	                BatteryT = intent.getIntExtra("temperature", 0);  //����¶�  
	                  
	                switch (intent.getIntExtra("status", BatteryManager.BATTERY_STATUS_UNKNOWN))   
	                {  
	                case BatteryManager.BATTERY_STATUS_CHARGING:  
	                    BatteryStatus = "���״̬";  
	                    break;  
	                case BatteryManager.BATTERY_STATUS_DISCHARGING:  
	                    BatteryStatus = "�ŵ�״̬";  
	                    break;  
	                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:  
	                    BatteryStatus = "δ���";  
	                    break;  
	                case BatteryManager.BATTERY_STATUS_FULL:  
	                    BatteryStatus = "������";  
	                    break;  
	                case BatteryManager.BATTERY_STATUS_UNKNOWN:  
	                    BatteryStatus = "δ֪��״̬";  
	                    break;  
	                }  
	                  
	                switch (intent.getIntExtra("health", BatteryManager.BATTERY_HEALTH_UNKNOWN))   
	                {  
	                case BatteryManager.BATTERY_HEALTH_UNKNOWN:  
	                    BatteryTemp = "δ֪����";  
	                    break;  
	                case BatteryManager.BATTERY_HEALTH_GOOD:  
	                    BatteryTemp = "״̬����";  
	                    break;  
	                case BatteryManager.BATTERY_HEALTH_DEAD:  
	                    BatteryTemp = "���û�е�";  
	                    break;  
	                case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:  
	                    BatteryTemp = "��ص�ѹ����";  
	                    break;  
	                case BatteryManager.BATTERY_HEALTH_OVERHEAT:  
	                    BatteryTemp =  "��ع���";  
	                    break;  
	                }  
	        TV.setText("Ŀǰ����Ϊ" + BatteryN + "% --- " + BatteryStatus + "\n" + "��ѹΪ" + BatteryV + "mV --- " + BatteryTemp + "\n" + "�¶�Ϊ" + (BatteryT*0.1) + "��");  
	            }  
	        }  
	    };  
	}  