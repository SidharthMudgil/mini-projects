import 'package:flutter/material.dart';

import '../model/transaction_model.dart';
import 'card_transaction.dart';

class TransactionList extends StatelessWidget {
  final List<Transaction> transactions;
  final Function deleteTransaction;

  const TransactionList(this.transactions, this.deleteTransaction, {super.key});

  @override
  Widget build(BuildContext context) {
    return transactions.isEmpty
        ? LayoutBuilder(
            builder: (ctx, constraint) {
              return Column(
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  SizedBox(
                    height: constraint.maxHeight * 0.1,
                    child: FittedBox(
                      child: Padding(
                        padding: const EdgeInsets.all(10),
                        child: Text(
                          'No transactions yet!!!',
                          style: Theme.of(context).textTheme.titleLarge,
                        ),
                      ),
                    ),
                  ),
                  SizedBox(
                    height: constraint.maxHeight * 0.05,
                  ),
                  SizedBox(
                    height: constraint.maxHeight * 0.6,
                    child: Image.asset(
                      'assets/images/waiting.png',
                      fit: BoxFit.cover,
                    ),
                  ),
                ],
              );
            },
          )
        : ListView.builder(
            itemBuilder: (buildContext, index) {
              return TransactionCard(
                  transactions.elementAt(index), deleteTransaction);
            },
            itemCount: transactions.length,
          );
  }
}
