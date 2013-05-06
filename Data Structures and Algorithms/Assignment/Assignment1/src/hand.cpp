//
//  File: hand.cpp
//  --------------
//
//  Created by Connor Livsey on 19/04/13.
//  Copyright (c) 2013 Connor Livsey. All rights reserved.
//

#include "hand.h"
#include "handCompare.h"


/*
 *  Hand - Constructor
 *--------------------------------------------------
 *  Sets player identifier
 */
Hand::Hand(int playerID)
{
    this->Id = playerID;
}

/*
 *	~Hand - Distructor
 *--------------------------------------------------
 *	Deletes hand vector
 */
Hand::~Hand()
{
    // NULL
}

/*
 *	AddCard
 *--------------------------------------------------
 *	Adds card to hand, check if full before adding
 */
void Hand::AddCard(Card *card)
{
    string playerString;
    out << Id;
    playerString = out.str();
    if(hand.size() >= CARDS_IN_HAND)
    {
        cerr << "Player " << playerString <<
             "'s Hand is FULL!!" << endl;
    }
    else
    {
        // Add card to the hand
        hand.push_back(card);
    }
}

/*
 * Evaluate
 *--------------------------------------------------
 *  Pre: Unevaluated hands
 *  Post: Evaluated hands
 *--------------------------------------------------
 */
void Hand::Evaluate()
{
    // Sort hand high to low
    sort(hand.begin(), hand.end(), CardComparer());
    EvaluateType();
    EvaluateValue();
}

/*
 * EvaluateType
 *--------------------------------------------------
 *  Pre:	Unknown hand
 *  Post:	Evaluated hand type
 *--------------------------------------------------
 *	Evaluates the hand on each hand type available
 *
 */
void Hand::EvaluateType()
{
    // Determine hand type.
    if(IsStraight() && 	IsFlush())
    {
        type = STRAIGHT_FLUSH;
    }
    else if (IsN_Of_A_Kind(FOUR, true))
    {
        type = FOUR_OF_A_KIND;
    }
    else if (	IsN_Of_A_Kind(THREE, false) && IsPair(PAIR, false, false))
    {
        type = FULL_HOUSE;
    }
    else if (IsFlush())
    {
        type = FLUSH;
    }
    else if (IsStraight())
    {
        type = STRAIGHT;
    }
    else if (IsN_Of_A_Kind(THREE, true))
    {
        type = THREE_OF_A_KIND;
    }
    else if (IsPair(PAIR, true, false))
    {
        type = TWO_PAIRS;
    }
    else if (IsPair(PAIR, false, false))
    {
        type = ONE_PAIR;
    }
    else
    {
        type = HIGH_CARD;
    }
}

/*
 * EvaluateValue
 *--------------------------------------------------
 *  Pre:	Predetermined hand type
 *  Post:	Hand value assigned
 *--------------------------------------------------
 *	Evaluates the value of each hand type
 *
 */
void Hand::EvaluateValue()
{
    // Determine value of hand type.
    switch(type)
    {
    case HIGH_CARD:
        EvalStandard();
        break;
    case ONE_PAIR:
        if (hand.at(0)->GetRank() == hand.at(1)->GetRank())
        {
            // a a x y z
            value = (pow(TOTAL_RANKS, 3) * hand.at(0)->GetRank())
                    + (pow(TOTAL_RANKS, 2) * hand.at(4)->GetRank())
                    + (TOTAL_RANKS * hand.at(3)->GetRank())
                    + hand.at(2)->GetRank();
        }
        else if (hand.at(1)->GetRank() == hand.at(2)->GetRank())
        {
            // x a a y z
            value = (pow(TOTAL_RANKS, 3) * hand.at(1)->GetRank())
                    + (pow(TOTAL_RANKS, 2) * hand.at(4)->GetRank())
                    + (TOTAL_RANKS * hand.at(3)->GetRank())
                    + hand.at(0)->GetRank();
        }
        else if (hand.at(2)->GetRank() == hand.at(3)->GetRank())
        {
            // x y a a z
            value = (pow(TOTAL_RANKS, 3) * hand.at(2)->GetRank())
                    + (pow(TOTAL_RANKS, 2) * hand.at(4)->GetRank())
                    + (TOTAL_RANKS * hand.at(1)->GetRank())
                    + hand.at(0)->GetRank();
        }
        else
        {
            // x y z a a
            value = (pow(TOTAL_RANKS, 3) * hand.at(3)->GetRank())
                    + (pow(TOTAL_RANKS, 2) * hand.at(2)->GetRank())
                    + (TOTAL_RANKS * hand.at(1)->GetRank())
                    + hand.at(0)->GetRank();
        }
        break;
    case TWO_PAIRS:
        if (hand.at(0)->GetRank() == hand.at(1)->GetRank()
                && hand.at(2)->GetRank() == hand.at(3)->GetRank())
        {
            // a a b b x
            value = (pow(TOTAL_RANKS, 2) * hand.at(2)->GetRank())
                    + (TOTAL_RANKS * hand.at(0)->GetRank())
                    + hand.at(FOUR)->GetRank();

        }
        else if (hand.at(0)->GetRank() == hand.at(1)->GetRank()
                 && hand.at(3)->GetRank() == hand.at(FOUR)->GetRank())
        {
            // a a x b b
            value = (pow(TOTAL_RANKS, 2) * hand.at(3)->GetRank())
                    + (TOTAL_RANKS * hand.at(0)->GetRank())
                    + hand.at(2)->GetRank();
        }
        else
        {
            // x a a b b
            value = (pow(TOTAL_RANKS, 2) * hand.at(3)->GetRank())
                    + (TOTAL_RANKS * hand.at(1)->GetRank())
                    + hand.at(0)->GetRank();
        }
        break;
    case THREE_OF_A_KIND:
        /*
         Possible "3 of a kind" hands:
            a a a x y
            x a a a y
            x y a a a
         */
        value = hand.at(2)->GetRank();
        break;
    case STRAIGHT:
        EvalStraight();
        break;
    case FLUSH:
        EvalStandard();
        break;
    case FULL_HOUSE:
        /*
         Possible "Full House" hands:
         a a a x x
         x x a a a
         */
        value = hand.at(2)->GetRank();
        break;
    case FOUR_OF_A_KIND:
        /*
         Possible "4 of a kind" hands:
         a a a a x
         x a a a a
         */
        value = hand.at(2)->GetRank();
        break;
    case STRAIGHT_FLUSH:
        EvalStraight();
        break;
    default:
        EvalStandard();
    }
}

/*
 * EvaluateStandard
 *--------------------------------------------------
 *  Pre:	Hand without value
 *  Post:	Hand with value
 *
 *	Evaluates the value of the hand
 *
 */
void Hand::EvalStandard()
{
    for (int i = CARDS_IN_HAND-1; i >= FIRST; --i)
    {
        value += pow ((hand.at(i)->GetRank() * TOTAL_RANKS), i);
    }
}

/*
 * EvaluateStraight
 *--------------------------------------------------
 *  Pre:	Straight hand without value
 *  Post:	Straight hand with value
 *
 *	Evaluates the value of a Straight hand
 *
 */
void Hand::EvalStraight()
{
    // Test for low ace
    if (hand.at(4)->GetRank() == ACE && hand.at(0)->GetRank() == FIRST)
    {
        for (int i = CARDS_IN_HAND-2; i >= FIRST; --i)
        {
            value += pow ((hand.at(i)->GetRank() * TOTAL_RANKS), i);
        }
        value += hand.at(4)->GetRank();
    }
    else // Normal Straight
    {
        for (int i = CARDS_IN_HAND-1; i > FIRST; --i)
        {
            value += pow ((hand.at(i)->GetRank() * TOTAL_RANKS), i);
        }
    }
}


/*
 * IsStraight
 *--------------------------------------------------
 *	Checks if hand is straight
 *  Low to High: Continuous Set
 *
 *	Returns: true if hand is straight
 */
bool Hand::IsStraight()
{
    int straight = FIRST;
    int handSize = CARDS_IN_HAND - ONE_CARD;
    // Determine if hand is lowest straight: ACE = 1
    if(hand.at(CARDS_IN_HAND-ONE_CARD)->GetRank() == ACE)
    {
        if(hand.at(FIRST)->GetRank()== TWO)
        {
            straight++;
            handSize--;
        }
    }
    // Determine if cards are sequential
    for (int card = FIRST; card < handSize; ++card)
    {
        if(hand.at(card + ONE_CARD)->GetRank()
                == hand.at(card)->GetRank() + ONE_RANK)
        {
            straight++;
        }
        else
        {
            // No straight
            return false;
        }
    }
    // True if straight found
    return (straight == CARDS_IN_HAND - ONE_CARD);
}

/*
 * IsFlush
 *--------------------------------------------------
 *	Check if all the cards have same suit
 *
 *	Returns: true if the hand is flush
 */
bool Hand::IsFlush()
{
    int flush = FIRST;
    // Determine if suits are sequential
    for (int card = FIRST+ONE_CARD; card < hand.size(); ++card)
    {
        if(hand.at(card)->GetSuit() == hand.at(FIRST)->GetSuit())
        {
            flush++;
        }
        else
        {
            // No flush
            return false;
        }
    }
    // True if flush found
    return (flush == CARDS_IN_HAND - ONE_CARD);
}


/*
 * IsN_Of_A_Kind
 *--------------------------------------------------
 *	Checks if hand is Three or Four of a Kind
 *
 *	Returns: true if a kind is found
 */
bool Hand::IsN_Of_A_Kind(Kind kind, bool sort)
{
    int found = FIRST_CARD;
    // Iterate over the potential number of kind
    for (int i = 0; i < CARDS_IN_HAND - kind; ++i)
    {
        for (int card = FIRST; card < kind; ++card)
        {
            if( hand.at(card+i)->GetRank() ==
                    hand.at(card+(ONE_CARD+(ONE_CARD * i)))->GetRank())
            {
                // Iterate until found == kind, or no kind found.
                found++;
                if(found == kind)
                {
                    if (sort)
                    {
                        for (int j = card + ONE_CARD; j > FIRST; --j)
                        {
                            Card *aKind = hand.at(card + ONE_CARD);
                            hand.erase(hand.begin() + (card + ONE_CARD));
                            hand.insert(hand.begin(), aKind);
                        }
                    }
                    // True if number of kind found
                    return true;
                }
            }
            else
            {
                found = ONE_CARD;
            }
        }
    }
    // Did not find kind
    return false;
}

/*
 * IsPair
 *--------------------------------------------------
 *	Check if hand is pair
 *	Check if 2 pairs of pairs
 *	Returns: true if a pair found
 *	Returns: true if 2 pairs found;
 */
bool Hand::IsPair(Kind kind, bool twoPairs, bool sort)
{
    int found = FIRST_CARD;
    int pair = 0;

    // Iterater through hand
    for (int card = 0; card < CARDS_IN_HAND - FIRST_CARD; ++card)
    {
        Card *card1 = hand.at(card);
        Card *card2 = hand.at(card + ONE_CARD);

        // pair found
        if(card1->GetRank() == card2->GetRank())
        {
            found++;
        }
        else
        {
            if(found == kind)
            {
                pair++;
                if(sort)
                {
                    Card *pair1 = hand.at(card);
                    Card *pair2 = hand.at(card - ONE_CARD);
                    hand.erase(hand.begin()+card);
                    hand.erase(hand.begin()+(card - ONE_CARD));
                    hand.insert(hand.begin(), pair1);
                    hand.insert(hand.begin(), pair2);
                }
            }
            found = FIRST_CARD;
        }
    }
    if(found == kind)
    {
        pair++;
        if(sort)
        {
            Card *pair1 = hand.at(CARDS_IN_HAND - 1);
            Card *pair2 = hand.at(CARDS_IN_HAND - 2);
            hand.erase(hand.begin()+(CARDS_IN_HAND - 1));
            hand.erase(hand.begin()+(CARDS_IN_HAND - 2));
            hand.insert(hand.begin(), pair1);
            hand.insert(hand.begin(), pair2);
        }
    }
    if(twoPairs && (pair == PAIR))
    {
        // Two pairs found
        return true;
    }
    else if(pair && !twoPairs)
    {
        // One pair found
        return true;
    }
    return false;
}

/*
 * ClearHand
 *--------------------------------------------------
 *  Pre:	Hand vector contains cards
 *  Post:	All cards removed from hand vector
 *--------------------------------------------------
 */
void Hand::ClearHand()
{
    hand.erase(hand.begin(), hand.end());
}

/*
 *	GetValue
 *--------------------------------------------------
 *  Returns: Value of hand
 */
int Hand::GetValue()
{
    return value;
}

/*
 *	GetHandType
 *--------------------------------------------------
 *  Returns: Hand type
 */
HandType Hand::GetHandType()
{
    return type;
}

/*
 *	ToString
 *--------------------------------------------------
 *  Returns: All the cards in hand
 */
string Hand::ToString()
{
    // Print only the first number
    // Work around
    string playerString;
    string formatedPlayer;
    char blankChar = ' ';
    int playerTen = 10;

    out << Id;
    playerString = out.str();
    if (Id != playerTen)
    {
        formatedPlayer =  blankChar + playerString.substr(0, 1);
    }
    else
    {
        formatedPlayer = playerString.substr(0, 2);
    }
    // Work around end

    sort(hand.begin(), hand.end(), CardComparer());
    string playersHand = "Player " + formatedPlayer + " -- ";

    // Print cards in players hand
    for (int card = FIRST; card < hand.size(); ++card)
    {
        playersHand += hand.at(card)->ToString() + " ";
    }

    return playersHand + "-- " + HAND_TYPE[type];
}

