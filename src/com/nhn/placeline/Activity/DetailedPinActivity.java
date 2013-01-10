package com.nhn.placeline.Activity;

import java.util.ArrayList;

import com.nhn.placeline.Activity.R;
import com.nhn.placeline.Activity.R.id;
import com.nhn.placeline.vo.PinContent;
import com.nhn.placeline.vo.PinReply;
import com.nhn.placeline.vo.User;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class DetailedPinActivity extends Activity implements OnClickListener, OnEditorActionListener{

	private ArrayList<PinReply> replyList;
	private PinContent pinContent;
	private User user1;
	private User user2;
	private User user3;
	private User user4;
	
	private LinearLayout replyItem;
	private LinearLayout photoAlbum;
	private TextView commentNoReply;
	private TextView commentReply;
	private TextView buttonSendReply;
	private EditText editTextReply;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detailed_pin);
		
		initInstance();
		initViews();
		displayPinInfo();
		displayPhotos();
		displayReplies();
	}
	
	
	// 버튼 리스너 
	@Override
	public void onClick(View button) {
		if (button.getId() == R.id.button_send_reply){
			sendReply();
		}
	}

	
	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		 if(actionId == EditorInfo.IME_ACTION_DONE){ // IME_ACTION_SEARCH , IME_ACTION_GO
			 sendReply();
          }
		return false;
	}
	
	private void initInstance(){
		user1 = new User("윤홍경", "010-6848-3855", R.drawable.user_1);
		user2 = new User("김성호", "010-6848-3855", R.drawable.user_2);
		user3 = new User("백준선", "010-6848-3855", R.drawable.user_3);
		user4 = new User("윤영제", "010-6848-3855", R.drawable.user_4);
		
		replyList = new ArrayList<PinReply>();
		replyList.add(new PinReply(0, user1, "살아있네살아있네살아있네살아있네살아있네살아있네살아있네" +
				"살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네" +
				"살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네살아있네"));
		replyList.add(new PinReply(1, user2, "그치?ㅋㅋㅋㅋㅋㅋㅋㅋ 나 지금 울상이야ㅜㅜ"));
		replyList.add(new PinReply(2, user3, "누나바보"));
		replyList.add(new PinReply(3, user4, "집에가고싶으무ㅜ ㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜ"));
		
		pinContent = new PinContent(0, "생애 첫교육", "살아있소 살아있소 살아있소 살아있소 살아있소 살아있소 살아있소 살아있소 살아있소 살아있소 살아있소 살아있소 흐하", user1);
		pinContent.addPictures(R.drawable.photo_1);
		pinContent.addPictures(R.drawable.photo_2);
		pinContent.addPictures(R.drawable.photo_3);
		pinContent.addPictures(R.drawable.photo_4);
	}
	
	private void initViews(){
		replyItem = (LinearLayout) findViewById(R.id.itemReplyList);
		photoAlbum = (LinearLayout) findViewById(R.id.horizontalScrollLayout);
		
		commentNoReply = (TextView) findViewById(R.id.msgNoReply);
		commentReply = (TextView) findViewById(R.id.comment_reply);
		buttonSendReply = (TextView) findViewById(R.id.button_send_reply);
		editTextReply = (EditText) findViewById(R.id.edittext_reply);
		buttonSendReply.setOnClickListener(this);
		editTextReply.setOnEditorActionListener(this);
	}
	
	private void displayPinInfo(){
		TextView pinTitle = (TextView) findViewById(id.detailed_pin_title);
		TextView contentWriter = (TextView) findViewById(id.content_writer);
		TextView contentContent = (TextView) findViewById(id.content_content);
		TextView contentDate = (TextView) findViewById(id.content_date);
		
		contentWriter.setText(pinContent.getWriter().getName());
		contentDate.setText(pinContent.getDateToString());
		contentContent.setText(pinContent.getContent());
		pinTitle.setText(pinContent.getTitle());
	}
	
	private void displayPhotos(){
		LayoutInflater mInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view;
		ImageView photo;
		
		for(int i=0; i<pinContent.countPictures(); i++){
			view = mInflater.inflate(R.layout.item_photo, null);
			photo = (ImageView) view.findViewById(R.id.item_photo);
			photo.setBackgroundResource(pinContent.getPictures(i));
			photoAlbum.addView(view);
		}
		
		view = mInflater.inflate(R.layout.item_add_photo, null);
		photo = (ImageView) view.findViewById(R.id.item_add_photo);
		photo.setBackgroundResource(R.drawable.detailed_pin_add_photo);
		photoAlbum.addView(view);
	}
	 
	private void displayReplies(){
		if(replyList.size() > 0){
			LayoutInflater mInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view;
			TextView replyWriter;
			TextView replyComment;
			TextView replyDate;
			ImageView replyPhoto;
			
			for(int i=0; i<replyList.size(); i++){
				mInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = mInflater.inflate(R.layout.item_replylist, null);
				replyWriter = (TextView) view.findViewById(id.reply_writer);
				replyComment = (TextView) view.findViewById(id.reply_comment);
				replyDate = (TextView) view.findViewById(id.reply_date);
				replyPhoto = (ImageView) view.findViewById(id.reply_photo);
				replyWriter.setText(replyList.get(i).getWriter().getName());
				replyComment.setText(replyList.get(i).getComments());
				replyDate.setText(replyList.get(i).getDateToString());
				replyPhoto.setBackgroundResource(replyList.get(i).getWriter().getThumnail());
				
				replyItem.addView(view);
			}
			commentReply.setText("댓글 (" + replyList.size() + ")");
		}
		else{
			commentNoReply.setVisibility(View.VISIBLE);
		}
	}
	
	private void sendReply(){
		 Log.d("########## [DEBUG] ##########","onClick() - Button_SendReply button is clicked / Reply : " + editTextReply.getText());
		 editTextReply.setText("");
	}
}
