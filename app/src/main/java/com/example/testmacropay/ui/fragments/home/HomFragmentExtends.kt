package com.example.testmacropay.ui.fragments.home

fun HomeFragment.initElements() {
    mBinding.apply {
        txtTitularValue.text = viewModel.getModel().titular
        txtURLValue.text = viewModel.getModel().url
        txtEmailValue.text = viewModel.getModel().email
        txtSolicitudValue.text = viewModel.getModel().solicitud
    }
}