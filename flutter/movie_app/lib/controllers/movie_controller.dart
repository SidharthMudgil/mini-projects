import 'package:flutter/cupertino.dart';
import 'package:get/get.dart';
import 'package:movie_app/models/movie_model.dart';

import '../services/remote_services.dart';

class MovieController extends GetxController {
  var movieList = <MovieModel>[].obs;
  // RxBool isLoading = false.obs;

  void fetchAlbum(String searchTitle) async {
    var movies = await RemoteServices.fetchMovies(searchTitle);
    // isLoading = RxBool(true);
    if (movies.isNotEmpty) {
      movieList.value = movies;
      update();
      debugPrint(movies.toString());
    }
    // isLoading = RxBool(false);
  }
}
