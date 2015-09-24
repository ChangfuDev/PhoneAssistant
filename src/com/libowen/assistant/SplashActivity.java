package com.libowen.assistant;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.guoshisp.mobilesafe.R;
import com.libowen.assistant.utils.AssetCopyUtil;

public class SplashActivity extends Activity {
	private TextView tv_splash_version;

	protected static final String TAG = "SplashActivity";
	private RelativeLayout rl_splash;

	/**
	 * ����������
	 */
	private void loadMainUI() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();// �ѵ�ǰ��Activity������ջ�����Ƴ�
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ����Ϊ�ޱ�����
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ����Ϊȫ��ģʽ
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_splash);
		rl_splash = (RelativeLayout) findViewById(R.id.rl_splash);
		tv_splash_version = (TextView) findViewById(R.id.tv_splash_version);
		tv_splash_version.setText("�汾��:" + getVersion());

		AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
		aa.setDuration(2000);
		rl_splash.startAnimation(aa);

		// ��������������ݿ��ļ�
		new Thread() {
			public void run() {
				File file = new File(getFilesDir(), "antivirus.db");
				if (file.exists() && file.length() > 0) {// ���ݿ��ļ��Ѿ������ɹ�

				} else {
					AssetCopyUtil.copy1(getApplicationContext(),
							"antivirus.db", file.getAbsolutePath(), null);
				}
			};

		}.start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(5000);
					loadMainUI();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();
	}

	/**
	 * ��ȡ��ǰӦ�ó���İ汾�š� �汾�Ŵ��������ǵ�APK�ж�Ӧ���嵥�ļ��У�ֱ�ӽ�ѹAPK�󣬼��ɿ�����Ӧ���嵥�ļ�����
	 * �汾����manifest�ڵ��е�android:versionName="1.0" ��һ��Ӧ�ó���װ���ֻ���
	 * ����apk�������ֻ���data/appĿ¼�£�Ҳ����ϵͳ�У�����ͼ6��������õ��汾�ţ�������Ҫ�õ���ϵͳ��صķ��񣬾Ϳ��Եõ�apk�е���Ϣ��
	 * 
	 * @return
	 */
	private String getVersion() {
		// �õ�ϵͳ�İ����������Ѿ��õ���apk���������İ�װ
		PackageManager pm = this.getPackageManager();
		try {
			// ����һ����ǰӦ�ó���İ��� ����������ѡ�ĸ�����Ϣ�����������ò��� �����Զ���Ϊ0
			PackageInfo info = pm.getPackageInfo(getPackageName(), 0);
			// ���ص�ǰӦ�ó���İ汾��
			return info.versionName;
		} catch (Exception e) {// ����δ�ҵ����쳣�������ϣ� ���쳣�����ܻᷢ��
			e.printStackTrace();
			return "";
		}
	}
}
