package com.redclifftech.imagepicker

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    companion object {

        private const val GALLERY_IMAGE_REQ_CODE = 101
        private const val CAMERA_IMAGE_REQ_CODE = 102

    }

    private var mCameraUri: Uri? = null
    private var mGalleryUri: Uri? = null

    var camera : Button? = null
    var gallery : Button? = null
    var picView : ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        camera = findViewById(R.id.camera_btn)
        gallery = findViewById(R.id.gallery_btn)
        picView = findViewById(R.id.imageView)

        camera?.setOnClickListener {

            ImagePicker.with(this)
                .cameraOnly()
                .crop()
                .start(CAMERA_IMAGE_REQ_CODE)
        }

        gallery?.setOnClickListener {

            ImagePicker.with(this)
                .galleryOnly()
                .crop()
                .start(GALLERY_IMAGE_REQ_CODE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val uri: Uri = data?.data!!
            when (requestCode) {
                GALLERY_IMAGE_REQ_CODE -> {
                    mGalleryUri = uri
                    picView?.setLocalImage(uri)
                }
                CAMERA_IMAGE_REQ_CODE -> {
                    mCameraUri = uri
                    picView?.setLocalImage(uri)
                }
            }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}