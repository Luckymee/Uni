//
//  File: main.cpp
//  --------------
//
//  Created by Connor Livsey on 18/04/13.
//  Copyright (c) 2013 Connor Livsey. All rights reserved.
//

#include <iostream>
#include <fstream>
#include <iomanip>
#include <vector>
#include <algorithm>

#include "deck.h"
#include "card.h"
#include "hand.h"
#include "handCompare.h"

using namespace std;

const int PLAYERS = 10;
const int CARDS_PER_PLAYER = 5;


int main(int argc, char *argv[])
{

    // Declare Deck and vector of pointer to Hand
    Deck *deck = new Deck();
    vector<Hand*> hand;

    // create Hand for each player
    for(int p = 1; p <= PLAYERS; ++p)
    {
        Hand *player = new Hand(p);
        hand.push_back(player);
    }

    // allow for testing from file
    if (argc == 2)
    {

        // open the file and check it exists
        ifstream infile;
        infile.open(argv[1]);
        if (infile.fail())
        {
            cerr <<  "Error: Could not find file" << endl;
            return 1;
        }

        // read the cards into the hands
        int rank, suit;
        for (int card = 0; card < CARDS_PER_PLAYER; ++card)
        {
            for (int i = 0; i < PLAYERS; ++i)
            {
                infile >> rank >> suit;
                Card *card = new Card((Rank)rank, (Suit)suit);
                hand.at(i)->AddCard(card);
            }
        }

        // close the file
        infile.close();
    }
    else
    {

        // shuffle
        deck->Shuffle();

        // deal the cards
        for(int card = 0; card < CARDS_PER_PLAYER; ++card)
        {
            for (int player = 0; player <  PLAYERS; ++player)
            {
                hand.at(player)->AddCard(deck->DealNextCard());
            }
        }
    }

    // evaluate the hands
    for (int player = 0; player < PLAYERS; ++player)
    {
        hand.at(player)->Evaluate();
    }
    // sort the hands
    sort(hand.rbegin(), hand.rend(), HandComparer());

    // output the hands
    for (int player = 0; player <  PLAYERS; ++player)
    {
        cout << hand.at(player)->ToString() << endl;
    }
    return 0;
}

