package com.libowen.assistant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.guoshisp.mobilesafe.R;
import com.libowen.assistant.adapter.MainAdapter;
public class MainActivity extends Activity {
	//��ʾ�������еľŴ�ģ���GridView
	private GridView gv_main;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		gv_main = (GridView) findViewById(R.id.gv_main);
		//Ϊgv_main��������һ������������������������������Ϊÿ��item����Ӧ������
		gv_main.setAdapter(new MainAdapter(this));
		//ΪGridView�����е�item���õ��ʱ�ļ����¼�
		gv_main.setOnItemClickListener(new OnItemClickListener() {
			//����һ��item�ĸ��ؼ���Ҳ����GridView ����������ǰ�����item ����������ǰ�����item��GridView�е�λ��
			//�����ģ�id��ֵΪ�����GridView����һ���Ӧ����ֵ�������GridView��9���id�͵���8
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0: //������
					Intent callSmsIntent = new Intent(MainActivity.this,CallSmsSafeActivity.class);
					startActivity(callSmsIntent);
					break;
				case 1: //�������
					Intent appManagerIntent = new Intent(MainActivity.this,AppManagerActivity.class);
					startActivity(appManagerIntent);
					break;
				case 2: //���̹���
					Intent taskManagerIntent = new Intent(MainActivity.this,TaskManagerActivity.class);
					startActivity(taskManagerIntent);
					break;
				case 3: //��������
					Intent trafficInfoIntent = new Intent(MainActivity.this,TrafficInfoActivity.class);
					startActivity(trafficInfoIntent);
					break;
				case 4: //�ֻ�ɱ��
					Intent antiVirusIntent = new Intent(MainActivity.this,AntiVirusActivity.class);
					startActivity(antiVirusIntent);
					break;
				case 5: //ϵͳ�Ż�
					Intent cleanCacheIntent = new Intent(MainActivity.this,CleanCacheActivity.class);
					startActivity(cleanCacheIntent);
					break;
				case 6://�߼�����
					Intent atoolsIntent = new Intent(MainActivity.this,AtoolsActivity.class);
					startActivity(atoolsIntent);
					break;
				case 7://��������
					//��ת�����������ġ���Ӧ��Activity����
					Intent settingIntent = new Intent(MainActivity.this,SettingCenterActivity.class);
					startActivity(settingIntent);
					break;
				}
			}
		});
	}
}

