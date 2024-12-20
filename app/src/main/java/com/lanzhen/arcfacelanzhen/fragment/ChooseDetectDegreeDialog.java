package com.lanzhen.arcfacelanzhen.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import static com.arcsoft.face.enums.DetectFaceOrientPriority.ASF_OP_0_ONLY;
import static com.arcsoft.face.enums.DetectFaceOrientPriority.ASF_OP_180_ONLY;
import static com.arcsoft.face.enums.DetectFaceOrientPriority.ASF_OP_270_ONLY;
import static com.arcsoft.face.enums.DetectFaceOrientPriority.ASF_OP_90_ONLY;
import static com.arcsoft.face.enums.DetectFaceOrientPriority.ASF_OP_ALL_OUT;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.lanzhen.arcfacelanzhen.R;
import com.lanzhen.arcfacelanzhen.util.ConfigUtil;

public class ChooseDetectDegreeDialog extends DialogFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View dialogView = inflater.inflate(R.layout.dialog_choose_detect_degree, container);
        initView(dialogView);
        return dialogView;
    }

    private void initView(View dialogView) {
        ImageView ivClose = dialogView.findViewById(R.id.iv_close);
        ivClose.setOnClickListener(this);
        //设置视频模式下的人脸优先检测方向
        RadioGroup radioGroupFtOrient = dialogView.findViewById(R.id.radio_group_ft_orient);
        RadioButton rbOrient0 = dialogView.findViewById(R.id.rb_orient_0);
        RadioButton rbOrient90 = dialogView.findViewById(R.id.rb_orient_90);
        RadioButton rbOrient180 = dialogView.findViewById(R.id.rb_orient_180);
        RadioButton rbOrient270 = dialogView.findViewById(R.id.rb_orient_270);
        RadioButton rbOrientAll = dialogView.findViewById(R.id.rb_orient_all);
        switch (ConfigUtil.getFtOrient(getActivity())) {
            case ASF_OP_90_ONLY:
                rbOrient90.setChecked(true);
                break;
            case ASF_OP_180_ONLY:
                rbOrient180.setChecked(true);
                break;
            case ASF_OP_270_ONLY:
                rbOrient270.setChecked(true);
                break;
            case ASF_OP_ALL_OUT:
                rbOrientAll.setChecked(true);
                break;
            case ASF_OP_0_ONLY:
            default:
                rbOrient0.setChecked(true);
                break;
        }
        radioGroupFtOrient.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_orient_90) {
                    ConfigUtil.setFtOrient(getActivity(), ASF_OP_90_ONLY);
                } else if (checkedId == R.id.rb_orient_180) {
                    ConfigUtil.setFtOrient(getActivity(), ASF_OP_180_ONLY);
                } else if (checkedId == R.id.rb_orient_270) {
                    ConfigUtil.setFtOrient(getActivity(), ASF_OP_270_ONLY);
                } else if (checkedId == R.id.rb_orient_all) {
                    ConfigUtil.setFtOrient(getActivity(), ASF_OP_ALL_OUT);
                } else {
                    ConfigUtil.setFtOrient(getActivity(), ASF_OP_0_ONLY);
                }
                dismiss();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (window != null){
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onClick(View view) {
        dismiss();
    }
}
