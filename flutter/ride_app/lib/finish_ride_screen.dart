import 'dart:io';

import 'package:image_picker/image_picker.dart';
import 'package:flutter/material.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:shared_preferences/shared_preferences.dart';

class FinishRideScreen extends StatefulWidget {
  static const route = '/finish_ride';

  const FinishRideScreen({Key? key}) : super(key: key);

  @override
  State<FinishRideScreen> createState() => _FinishRideScreenState();
}

class _FinishRideScreenState extends State<FinishRideScreen> {
  File? _photo;

  Future<void> _getFromCamera() async {
    XFile? photo = await ImagePicker().pickImage(
      source: ImageSource.camera,
      maxHeight: 1080,
      maxWidth: 1080,
    );
    setState(() {
      _photo = File(photo!.path);
    });
  }

  @override
  Widget build(BuildContext context) {
    final args =
        ModalRoute.of(context)!.settings.arguments as Map<String, String>;
    return Scaffold(
      body: Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: [
          SizedBox(
              child: _photo != null
                  ? Image.file(_photo!)
                  : Image.asset('images/noimage.jpg')),
          if (_photo != null)
            SizedBox(
              height: 50,
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: [
                  ElevatedButton(
                      onPressed: () {
                        setState(() {
                          _photo = null;
                        });
                      },
                      child: const Text("Retake")),
                  ElevatedButton(
                    onPressed: () async {
                      final pref = await SharedPreferences.getInstance();
                      args['finalImage'] = _photo.toString();
                      FirebaseFirestore.instance
                          .collection("data")
                          .add(args)
                          .then((DocumentReference doc) {
                        debugPrint('DocumentSnapshot added with ID: ${doc.id}');
                        pref.clear();
                      }).onError((error, stackTrace) => null);

                      if (!mounted) return;
                      Navigator.of(context).pushReplacementNamed('/');
                    },
                    child: const Text("Finish Ride"),
                  ),
                ],
              ),
            ),
        ],
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
      floatingActionButton: Visibility(
        visible: _photo == null,
        child: FloatingActionButton(
          onPressed: _getFromCamera,
          child: const Icon(Icons.camera_alt_rounded),
        ),
      ),
    );
  }
}
