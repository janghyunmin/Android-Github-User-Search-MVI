package com.kkjang.deeplink

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.Navigator
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions

fun Fragment.navController(action: NavController.() -> Unit) {
    findNavController().action()
}

fun Fragment.navPopBack(
    @IdRes destinationId: Int? = null,
    inclusive: Boolean = true
) = navController {
    if (destinationId == null) popBackStack() else popBackStack(destinationId, inclusive)
}

val noneOption by lazy {
    NavOptionBuilders.None.build()
}

object NavOptionBuilders {
    val None
        get() = NavOptions.Builder()
}

fun Fragment.deepLink(
    deepLink: DeepLink,
    optionsBuilder: NavOptionsBuilder.() -> Unit
) = deepLink(deepLink.uri, navOptions(optionsBuilder))

fun Fragment.deepLink(
    uri: Uri,
    optionsBuilder: NavOptionsBuilder.() -> Unit
) = deepLink(uri, navOptions(optionsBuilder))

fun Fragment.deepLink(
    deepLink: DeepLink,
    navOption: NavOptions = noneOption
) = deepLink(deepLink.uri, navOption)

fun Fragment.deepLink(
    uri: Uri,
    navOption: NavOptions = noneOption
) = navController {
    navigate(uri, navOption)
}

fun Fragment.deepLink(
    direction: NavDirections,
    extras: Navigator.Extras? = null,
    optionsBuilder: NavOptionsBuilder.() -> Unit
) = navigate(direction.actionId, direction.arguments, extras, optionsBuilder)

fun Fragment.navigate(
    direction: NavDirections,
    navOption: NavOptions = noneOption,
    extras: Navigator.Extras? = null
) = navigate(direction.actionId, direction.arguments, navOption, extras)

fun Fragment.navigate(
    direction: NavDirections,
    extras: Navigator.Extras? = null,
    optionsBuilder: NavOptionsBuilder.() -> Unit
) = navigate(direction.actionId, direction.arguments, extras, optionsBuilder)

fun Fragment.navigate(
    @IdRes resId: Int,
    args: Bundle? = null,
    extras: Navigator.Extras? = null,
    optionsBuilder: NavOptionsBuilder.() -> Unit
) = navigate(resId, args, navOptions(optionsBuilder), extras)

fun Fragment.navigate(
    @IdRes resId: Int,
    args: Bundle? = null,
    navOption: NavOptions = noneOption,
    extras: Navigator.Extras? = null
) = findNavController().navigate(resId, args, navOption, extras)

fun View.navigate(
    direction: NavDirections,
    navOption: NavOptions = noneOption,
    extras: Navigator.Extras? = null
) = navigate(direction.actionId, direction.arguments, navOption, extras)

fun View.navigate(
    @IdRes resId: Int,
    args: Bundle? = null,
    navOption: NavOptions = noneOption,
    extras: Navigator.Extras? = null
) = findNavController().navigate(resId, args, navOption, extras)