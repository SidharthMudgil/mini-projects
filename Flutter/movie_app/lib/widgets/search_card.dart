import 'package:flutter/material.dart';

class SearchCard extends StatelessWidget {
  final TextEditingController _textController;
  final VoidCallback _onTap;

  const SearchCard(this._textController, this._onTap, {super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(border: Border.all(width: 1)),
      child: SizedBox(
        width: double.infinity,
        child: Row(
          crossAxisAlignment: CrossAxisAlignment.center,
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Expanded(
              child: TextField(
                controller: _textController,
                onSubmitted: (val) {
                  debugPrint(val);
                  _onTap;
                },
                textInputAction: TextInputAction.search,
                decoration: const InputDecoration(
                    contentPadding: EdgeInsets.all(10),
                    border: InputBorder.none,
                    hintStyle: TextStyle(color: Colors.black),
                    hintText: 'Search for movies'),
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(10),
              child: InkWell(
                onTap: _onTap,
                child: const Icon(
                  Icons.search,
                  color: Colors.black,
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
