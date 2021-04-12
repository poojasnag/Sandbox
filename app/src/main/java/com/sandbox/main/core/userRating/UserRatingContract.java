package com.sandbox.main.core.userRating;

import android.content.Context;
/**
 * Contract class for UserRatingActivity
 * @author Pooja Srinivas Nag
 * @author Mun Kei Wuai
 * @author Tan Wen Xiu
 */

public interface UserRatingContract {
    interface View {
        void onSubmitSelect(Context context);
    }
}
