import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:movie_app/controllers/movie_controller.dart';

import '../widgets/movie_card.dart';
import '../widgets/search_card.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  final TextEditingController _textController = TextEditingController();
  final MovieController _movieController = Get.put(MovieController());

  void onSearch() {
    FocusManager.instance.primaryFocus?.unfocus();

    if (_textController.text.isEmpty) {
      setState(() {
        _movieController.movieList.clear();
      });
    } else {
      var title = _textController.text;
      _textController.clear();
      _movieController.fetchAlbum(title);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        padding: const EdgeInsets.all(15),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const SizedBox(height: 40),
            const Text(
              'Home',
              style: TextStyle(
                color: Colors.black,
                fontSize: 32,
                fontFamily: 'sf',
                fontWeight: FontWeight.w700,
              ),
            ),
            const SizedBox(height: 20),
            SearchCard(_textController, onSearch),
            const SizedBox(height: 20),
            // if (_movieController.isLoading.isTrue)
            //   const Center(child: CircularProgressIndicator()),
            // if (_movieController.isLoading.isFalse)
            Expanded(
              child: Obx(
                () => ListView.builder(
                  padding: EdgeInsets.zero,
                  itemBuilder: (context, index) =>
                      MovieCard(_movieController.movieList.elementAt(index)),
                  itemCount: _movieController.movieList.length,
                  shrinkWrap: true,
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
