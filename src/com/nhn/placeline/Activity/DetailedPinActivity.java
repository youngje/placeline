package com.nhn.placeline.Activity;

import java.sql.Wrapper;
import java.util.ArrayList;

import com.nhn.placeline.Activity.R;
import com.nhn.placeline.Activity.R.id;
import com.nhn.placeline.constants.Constants;
import com.nhn.placeline.util.GroupAdapter;
import com.nhn.placeline.vo.Group;
import com.nhn.placeline.vo.PinContent;
import com.nhn.placeline.vo.PinReply;
import com.nhn.android.mapviewer.NMapViewer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DetailedPinActivity extends Activity {

	private ArrayList<PinReply> replyList;
	private PinContent pinContent;
	private String userid = "oskar";
	
	private LinearLayout replyItem;
	private TextView commentNoReply;
	private TextView commentReply;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detailed_pin);
		
		initInstance();
		initViews();
		displayPinInfo();
		displayReplies();
	}
	
	private void initInstance(){
		replyList = new ArrayList<PinReply>();
		replyList.add(new PinReply(0, "윤홍경", "살아있네살아있네살아있네살아있네살아있네살아있네살아있네" +
				"살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네" +
				"살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네"));
		replyList.add(new PinReply(1, "윤홍경", "그치?ㅋㅋㅋㅋㅋㅋㅋㅋ 나 지금 울상이야ㅜㅜ"));
		replyList.add(new PinReply(2, "윤홍경", "누나바보"));
		replyList.add(new PinReply(3, "윤홍경", "집에가고싶으무ㅜ ㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜ"));
		
		pinContent = new PinContent(0, "생애 첫교육", "살아있소 살아있소 살아있소 살아있소 살아있소 살아있소 살아있소 살아있소 살아있소 살아있소 살아있소 살아있소 흐하", "백준선");
	}
	
	private void initViews(){
		replyItem = (LinearLayout) findViewById(R.id.itemReplyList);
		commentNoReply = (TextView) findViewById(R.id.msgNoReply);
		commentReply = (TextView) findViewById(R.id.comment_reply);
	}
	
	private void displayPinInfo(){
		TextView pinTitle = (TextView) findViewById(id.detailed_pin_title);
		TextView contentWriter = (TextView) findViewById(id.content_writer);
		TextView contentContent = (TextView) findViewById(id.content_content);
		TextView contentDate = (TextView) findViewById(id.content_date);
		
		contentWriter.setText(pinContent.getWriter());
		contentDate.setText(pinContent.getDateToString());
		contentContent.setText(pinContent.getContent());
		pinTitle.setText(pinContent.getTitle());
	}
	 
	private void displayReplies(){
		if(replyList.size() > 0){
			for(int i=0; i<replyList.size(); i++){
				LayoutInflater mInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View view = mInflater.inflate(R.layout.item_replylist, null);
				TextView replyWriter = (TextView) view.findViewById(id.reply_writer);
				TextView replyComment = (TextView) view.findViewById(id.reply_comment);
				TextView replyDate = (TextView) view.findViewById(id.reply_date);
				replyWriter.setText(replyList.get(i).getWriter());
				replyComment.setText(replyList.get(i).getComments());
				replyDate.setText(replyList.get(i).getDateToString());

				replyItem.addView(view);
			}
			commentReply.setText("댓글 (" + replyList.size() + ")");
		}
		else{
			commentNoReply.setVisibility(View.VISIBLE);
		}
	}
}
