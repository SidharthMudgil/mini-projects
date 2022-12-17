import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';
import 'package:meditation_app/home/models/recommended_model.dart';

class RecommendedCard extends StatelessWidget {
  final int _index;
  final RecommendedModel _model;

  const RecommendedCard(this._index, this._model, {super.key});

  LinearGradient _getGradient() {
    switch (_index % 4) {
      case 0:
        return const LinearGradient(
          colors: [
            Color.fromRGBO(68, 29, 252, 1.0),
            Color.fromRGBO(78, 129, 235, 1.0),
          ],
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
        );
      case 1:
        return const LinearGradient(
          colors: [
            Color.fromRGBO(252, 103, 167, 1.0),
            Color.fromRGBO(246, 129, 91, 1.0),
          ],
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
        );
      case 2:
        return const LinearGradient(
          colors: [
            Color.fromRGBO(6, 183, 130, 1.0),
            Color.fromRGBO(19, 220, 160, 1.0),
          ],
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
        );
      case 3:
        return const LinearGradient(
          colors: [
            Color.fromRGBO(240, 179, 26, 1.0),
            Color.fromRGBO(255, 213, 65, 1.0),
          ],
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
        );
    }
    return const LinearGradient(
      colors: [
        Color.fromRGBO(68, 29, 252, 1.0),
        Color.fromRGBO(78, 129, 235, 1.0),
      ],
      begin: Alignment.topLeft,
      end: Alignment.bottomRight,
    );
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Container(
        margin: const EdgeInsets.only(right: 20),
        height: 200,
        width: 300,
        decoration: BoxDecoration(
          gradient: _getGradient(),
          borderRadius: const BorderRadius.all(Radius.circular(20)),
        ),
        child: Stack(
          children: [
            ClipRRect(
              borderRadius: BorderRadius.circular(20),
              child: SizedBox(
                height: double.maxFinite,
                width: double.maxFinite,
                child: SvgPicture.asset(
                  'images/pattern_bottom.svg',
                  fit: BoxFit.cover,
                  clipBehavior: Clip.hardEdge,
                ),
              ),
            ),
            ClipRRect(
              borderRadius: BorderRadius.circular(20),
              child: SizedBox(
                height: double.maxFinite,
                width: double.maxFinite,
                child: SvgPicture.asset(
                  'images/pattern_top.svg',
                  fit: BoxFit.cover,
                  clipBehavior: Clip.hardEdge,
                ),
              ),
            ),
            Positioned(
              top: 30,
              left: 20,
              child: SizedBox(
                width: 250,
                child: Text(
                  _model.title,
                  style: const TextStyle(
                    color: Colors.white,
                    fontFamily: 'sf',
                    fontSize: 24,
                    fontWeight: FontWeight.w600,
                  ),
                ),
              ),
            ),
            Positioned(
              top: 60,
              left: 20,
              child: SizedBox(
                width: 250,
                child: Text(
                  _model.subtitle,
                  style: const TextStyle(
                    color: Colors.white,
                    fontFamily: 'sf',
                    fontSize: 16,
                    fontWeight: FontWeight.w200,
                  ),
                ),
              ),
            ),
            Positioned(
              left: 20,
              bottom: 30,
              child: Row(
                crossAxisAlignment: CrossAxisAlignment.center,
                children: _model.assets
                    .map((e) => Container(
                        margin: const EdgeInsets.only(right: 10),
                        child: SvgPicture.asset(e)))
                    .toList(),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
