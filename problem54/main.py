# Card and suit values for comparison
card_values = {'2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9, 'T': 10, 'J': 11, 'Q': 12, 'K': 13, 'A': 14}
suit_values = {'H': 0, 'D': 1, 'C': 2, 'S': 3}

# Function to parse a hand into a sorted list of tuples (value, suit)
def parse_hand(hand):
    return sorted([(card_values[card[0]], suit_values[card[1]]) for card in hand], key=lambda x: x[0], reverse=True)

# Evaluate hand ranks
def evaluate_hand(hand):
    values = [card[0] for card in hand]
    suits = [card[1] for card in hand]
    is_flush = len(set(suits)) == 1
    is_straight = all(values[i] - values[i + 1] == 1 for i in range(len(values) - 1))

    value_counts = {value: values.count(value) for value in set(values)}
    sorted_value_counts = sorted(value_counts.items(), key=lambda x: (-x[1], -x[0]))

    if is_straight and is_flush:
        return (8, values)
    elif sorted_value_counts[0][1] == 4:
        return (7, sorted_value_counts[0][0])
    elif sorted_value_counts[0][1] == 3 and sorted_value_counts[1][1] == 2:
        return (6, sorted_value_counts[0][0])
    elif is_flush:
        return (5, values)
    elif is_straight:
        return (4, values)
    elif sorted_value_counts[0][1] == 3:
        return (3, sorted_value_counts[0][0])
    elif sorted_value_counts[0][1] == 2 and sorted_value_counts[1][1] == 2:
        return (2, sorted_value_counts[0][0], sorted_value_counts[1][0])
    elif sorted_value_counts[0][1] == 2:
        return (1, sorted_value_counts[0][0])
    else:
        return (0, values)

# Compare two hands
def compare_hands(hand1, hand2):
    rank1 = evaluate_hand(hand1)
    rank2 = evaluate_hand(hand2)
    return rank1 > rank2

# Count player 1 wins
player1_wins = 0
with open('poker.txt', 'r') as file:
    for line in file:
        cards = line.strip().split(' ')
        hand1 = parse_hand(cards[:5])
        hand2 = parse_hand(cards[5:])
        if compare_hands(hand1, hand2):
            player1_wins += 1

print(player1_wins)