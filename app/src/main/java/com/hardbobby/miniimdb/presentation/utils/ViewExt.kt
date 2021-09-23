import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.hardbobby.miniimdb.R
import com.hardbobby.miniimdb.presentation.utils.orTrue

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.setInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.setGone() {
    this.visibility = View.GONE
}

fun View.showIf(condition : () -> Boolean) {
    if (condition.invoke()) setVisible()
    else setGone()
}

fun View.showSlidingUpIf(predicate: Boolean) {
    if (predicate) showSlideUp()
    else hideSlideDown()
}
fun View.showSlidingDownIf(predicate: Boolean) {
    if (predicate) showSlideDown()
    else hideSlideUp()
}

fun View.showSlideUp() {
    if (this.visibility != View.VISIBLE) {
        setVisible()
        val bottomMargin = (layoutParams as ViewGroup.MarginLayoutParams).bottomMargin
        startAnimation(
            TranslateAnimation(
                0f,
                0f,
                height.toFloat() + bottomMargin.toFloat(),
                0f
            ).apply {
                duration = 500
            })
    }
}

fun View.hideSlideDown() {
    if (this.visibility == View.VISIBLE) {
        setGone()
        if (this.animation?.hasEnded().orTrue()) {
            val bottomMargin = (layoutParams as ViewGroup.MarginLayoutParams).bottomMargin
            startAnimation(
                TranslateAnimation(
                    0f,
                    0f,
                    0f,
                    height.toFloat() + bottomMargin.toFloat()
                ).apply {
                    duration = 500
                })
        } else {
            this.animation?.cancel()
        }
    }
}

fun View.showSlideDown() {
    if (this.visibility != View.VISIBLE) {
        setVisible()
        val bottomMargin = (layoutParams as ViewGroup.MarginLayoutParams).bottomMargin
        startAnimation(TranslateAnimation(0f, 0f, 0f, height.toFloat() + bottomMargin.toFloat()).apply {
            duration = 500
        })
    }
}

fun View.hideSlideUp() {
    if (this.visibility == View.VISIBLE) {
        setGone()
        if (this.animation?.hasEnded().orTrue()) {
            val bottomMargin = (layoutParams as ViewGroup.MarginLayoutParams).bottomMargin
            startAnimation(
                TranslateAnimation(
                    0f,
                    0f,
                    height.toFloat() + bottomMargin.toFloat(),
                    0f
                ).apply {
                    duration = 500
                })
        } else {
            this.animation?.cancel()
        }
    }
}

fun View.showOrHide(isShow: Boolean, hideVisibility: Int = View.GONE) {
    visibility = if (isShow) View.VISIBLE else hideVisibility
}

inline fun View.setOnClickWithThrottle(
    threshold: Long = 1000L,
    crossinline action: (v: View) -> Unit
) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0
        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < threshold) return
            else action(v)
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun View.showErrorSnackbar(message: String, length: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, message, length).apply {
        setBackgroundTint(ContextCompat.getColor(this.context, R.color.red_pome))
        show()
    }
}


fun View.showSuccessSnackbar(message: String, length: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, message, length).apply {
        setBackgroundTint(ContextCompat.getColor(this.context, R.color.green_jade))
        show()
    }
}