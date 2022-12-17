import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';

import '../models/recent_model.dart';

class RecentCard extends StatelessWidget {
  final int _index;
  final RecentModel _model;

  const RecentCard(this._index, this._model, {super.key});

  LinearGradient _getGradient() {
    switch (_index % 4) {
      case 0:
        return const LinearGradient(
          colors: [
            Color.fromRGBO(6, 183, 130, 1.0),
            Color.fromRGBO(19, 220, 160, 1.0),
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
            Color.fromRGBO(240, 179, 26, 1.0),
            Color.fromRGBO(255, 213, 65, 1.0),
          ],
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
        );
      case 3:
        return const LinearGradient(
          colors: [
            Color.fromRGBO(68, 29, 252, 1.0),
            Color.fromRGBO(78, 129, 235, 1.0),
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
    return Container(
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
                'images/pattern_bottom_small.svg',
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
                'images/pattern_top_small.svg',
                fit: BoxFit.cover,
                clipBehavior: Clip.hardEdge,
              ),
            ),
          ),
          Positioned(
            top: 20,
            left: 20,
            child: SizedBox(
              width: 100,
              child: Text(
                _model.label,
                style: const TextStyle(
                  color: Colors.white,
                  fontFamily: 'sf',
                  fontSize: 18,
                  fontWeight: FontWeight.w600,
                ),
              ),
            ),
          ),
          Positioned(
            left: 20,
            bottom: 20,
            child: SvgPicture.asset(_model.asset),
          ),
        ],
      ),
    );
  }
}
