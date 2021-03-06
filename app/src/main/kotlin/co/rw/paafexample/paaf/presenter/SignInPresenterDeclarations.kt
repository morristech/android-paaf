package co.rw.paafexample.paaf.presenter

import co.rw.paafexample.paaf.base.SignInAction
import co.rw.paafexample.paaf.base.SignInClickEvent
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import java.util.concurrent.TimeUnit

data class SignInViewModel(val signInActionChannel: ReceiveChannel<SignInAction>)

fun signInPresenter(clickEventChannel: ReceiveChannel<SignInClickEvent>): SignInViewModel {
    val signInActionChannel = Channel<SignInAction>()

    launch {
        for (signInClickEvent in clickEventChannel)
            when (signInClickEvent) {
                SignInClickEvent.SignInButton -> {
                    // Do some real sign in, for the example we will just delay
                    delay(time = 1000, unit = TimeUnit.MILLISECONDS)
                    signInActionChannel.send(SignInAction.SignInSuccessful)
                }
            }
    }

    return SignInViewModel(signInActionChannel)
}
