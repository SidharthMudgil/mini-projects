import 'package:flutter/material.dart';

import '../constants/constants.dart';
import '../service/lg_service.dart';
import '../widgets/input_field.dart';

const String connect = "Connect";
const String disconnect = "Disconnect";

class ConnectionScreen extends StatefulWidget {
  static const route = "/connection";

  const ConnectionScreen({super.key});

  @override
  State<ConnectionScreen> createState() => _ConnectionScreenState();
}

class _ConnectionScreenState extends State<ConnectionScreen> {
  final TextEditingController userController = TextEditingController();
  final TextEditingController passController = TextEditingController();
  final TextEditingController ipController = TextEditingController();
  final TextEditingController portController = TextEditingController();

  double _slaves = 3;
  bool _connected = false;

  @override
  void initState() {
    super.initState();
    _isConnected();
  }

  void _isConnected() async {
    final connected = await LGService.isConnected();
    setState(() {
      _connected = connected;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Connection Manager"),
        leading: IconButton(
          onPressed: () {
            Navigator.of(context).pop();
          },
          icon: const Icon(Icons.arrow_back),
        ),
      ),
      body: Row(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          SizedBox(
            width: 450,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.center,
              mainAxisAlignment: MainAxisAlignment.start,
              children: [
                const SizedBox(height: 16),
                const Text(
                  'Establish connection with LG',
                  style: TextStyle(
                    fontSize: 20,
                    fontWeight: FontWeight.bold,
                  ),
                  textAlign: TextAlign.center,
                ),
                const SizedBox(height: 8),
                Text(
                  _connected ? 'Connected' : 'Disconnected',
                  style: TextStyle(
                    color: _connected ? Colors.green : Colors.red,
                    fontSize: 28,
                    fontWeight: FontWeight.bold,
                  ),
                ),
                const SizedBox(height: 28),
                InputField(
                  label: "Username",
                  hint: "lg",
                  controller: userController,
                  type: TextInputType.name,
                  prefixIcon: Icons.person_rounded,
                ),
                const SizedBox(height: 16),
                InputField(
                  label: "Password",
                  hint: "lg",
                  controller: passController,
                  type: TextInputType.visiblePassword,
                  prefixIcon: Icons.key_rounded,
                  suffixIcons: const [
                    Icons.visibility_rounded,
                    Icons.visibility_off_rounded,
                  ],
                ),
                const SizedBox(height: 16),
                InputField(
                  label: "IP Address",
                  hint: "192.168.0.1",
                  controller: ipController,
                  type: TextInputType.phone,
                  prefixIcon: Icons.router_rounded,
                ),
                const SizedBox(height: 16),
                InputField(
                  label: "Port Number",
                  hint: "22",
                  controller: portController,
                  type: TextInputType.number,
                  prefixIcon: Icons.account_tree_rounded,
                ),
                const SizedBox(height: 28),
                Slider(
                  value: _slaves,
                  min: 3,
                  max: 7,
                  divisions: 4,
                  label: "${_slaves.toInt()}",
                  onChanged: (newValue) {
                    setState(() {
                      _slaves = newValue;
                    });
                  },
                  inactiveColor: Constants.blue100,
                  activeColor: Constants.blue200,
                  thumbColor: Constants.blue200,
                ),
                const SizedBox(height: 28),
                Align(
                  alignment: Alignment.center,
                  child: FilledButton(
                    onPressed: _connectToLiquidGalaxy,
                    style: FilledButton.styleFrom(
                      padding: const EdgeInsets.symmetric(
                        vertical: 20,
                        horizontal: 24,
                      ),
                      backgroundColor: Constants.blue100,
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(50),
                      ),
                    ),
                    child: Text(
                      _connected ? disconnect : connect,
                      style: const TextStyle(
                        fontSize: 16,
                        fontWeight: FontWeight.w700,
                        color: Constants.blue200,
                      ),
                    ),
                  ),
                )
              ],
            ),
          ),
        ],
      ),
    );
  }

  bool _isValidData() {
    return ipController.text.isNotEmpty &&
        portController.text.isNotEmpty &&
        userController.text.isNotEmpty &&
        passController.text.isNotEmpty;
  }

  Future<void> _connectToLiquidGalaxy() async {
    if (!_isValidData()) {
      showSnackBar("Invalid Data");
      return;
    }

    final lgService = LGService(
      host: ipController.text,
      port: int.parse(portController.text),
      username: userController.text,
      password: passController.text,
      slaves: _slaves.toInt(),
    );

    final result = await lgService.connect();
    if (result) {
      _isConnected();
      setState(() {
        _connected = true;
      });
      showSnackBar("successful");
    } else {
      _connected = false;
      _isConnected();
      showSnackBar("failed");
    }
  }

  void showSnackBar(String msg) {
    final snackBar = SnackBar(
      content: Text(msg),
      duration: const Duration(seconds: 3),
    );

    ScaffoldMessenger.of(context).showSnackBar(snackBar);
  }
}
