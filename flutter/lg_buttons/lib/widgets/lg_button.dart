import 'package:flutter/material.dart';

class LGButton extends StatelessWidget {
  final String label;
  final IconData icon;
  final Function() onPressed;
  final bool enabled;

  const LGButton({
    required this.label,
    required this.icon,
    required this.onPressed,
    required this.enabled,
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(top: 20),
      child: SizedBox(
        width: 350,
        height: 50,
        child: ElevatedButton(
          onPressed: () {
            if (enabled) {
              onPressed();
            }
          },
          style: ElevatedButton.styleFrom(
            backgroundColor: Theme.of(context).cardColor,
          ),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Text(
                label.toUpperCase(),
                style: const TextStyle(
                  color: Color.fromARGB(255, 159, 202, 255),
                  fontWeight: FontWeight.bold,
                  fontSize: 16,
                ),
              ),
              Icon(
                icon,
                color: const Color.fromARGB(255, 159, 202, 255),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
