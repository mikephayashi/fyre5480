# A simple class (struct-like) describing the location of an item in a matrix
class Position:
    def __init__(self, r, c):
        self.row = r
        self.column = c

    # For comparison
    def __eq__(self, other):
        if isinstance(other, self.__class__):
            return self.row == other.row and self.column == other.column

    # So that it can go in sets
    def __hash__(self):
        return hash((self.row, self.column))


# An object describing an item in a matrix that contains some helpful methods
class Node:
    def __init__(self, row, column, val):
        self.pos = Position(row, column)
        self.value = val

    # Returns the nodes adjacent (left, right, top, bottom) to self in a given matrix
    def neighbors(self, matrix):
        nodes = []

        y = self.pos.row
        x = self.pos.column

        try:
            if x - 1 >= 0:
                to_left = matrix[y][x-1].value
                nodes.append(Node(y, x-1, to_left))
        except: pass

        try:
            if x + 1 <= len(matrix[y]) - 1:
                to_right = matrix[y][x+1].value
                nodes.append(Node(y, x+1, to_right))
        except: pass

        try:
            if y - 1 >= 0:
                to_top = matrix[y-1][x].value
                nodes.append(Node(y-1, x, to_top))
        except: pass

        try:
            if y + 1 <= len(matrix) - 1:
                to_bottom = matrix[y+1][x].value
                nodes.append(Node(y+1, x, to_bottom))
        except: pass

        return nodes

    # Returns the nodes with the same value as self of self's neighbors in a given matrix
    def value_neighbors(self, matrix):
        return [node for node in self.neighbors(matrix) if node.value == self.value]

    # Looks prettier when printing
    # def __str__(self):
    #     return `self.value, (self.pos.column, self.pos.row)`[1:-1]

    # So that Nodes can go in sets
    def __hash__(self):
        return hash((self.pos, self.value))

    # Turns a matrix into one containing Nodes
    @staticmethod
    def nodify(matrix):
        for y, row in enumerate(matrix):
            for x, item in enumerate(row):
                node = Node(y, x, item)
                matrix[y][x] = node
        return matrix

    # Takes apart a matrix with Nodes to just contain the values
    @staticmethod
    def denodify(matrix):
        for y, row in enumerate(matrix):
            for x, node in enumerate(row):
                matrix[y][x] = node.value
        return matrix

    # For comparison
    def __eq__(self, other):
        if isinstance(other, self.__class__):
            return self.value == other.value and self.pos == other.pos
        return False


# A global set containing nodes already visited so that infinite loops do not occur
already_checked = set()


# A recursive method returning the continuous network of value_neighbors in a matrix starting from a node
def connected_values(node, matrix):
    global already_checked

    already_checked.add(node)

    nodes = []
    for value_neighbor in node.value_neighbors(matrix):
        nodes.append(value_neighbor)
        if value_neighbor not in already_checked:
            nodes += connected_values(value_neighbor, matrix)
    return nodes


# A method that gets all of the connected values networks in a matrix
def all_connected_values(matrix):
    global already_checked

    groups = []

    for row in matrix:
        for node in row:
            already_checked = set()

            values = set(connected_values(node, matrix))
            values.add(node)
            groups.append(values)

    # Formats the networks and prints them out. A set is used so that duplicate networks are not shown
    print '\n'.join({str(group[0].value) + ": " + ', '.join(map(str, sorted([(node.pos.column, node.pos.row) for node in group], key=lambda t:[t[0],t[1]]))) for group in map(list, groups)})
    print
    # Prints out the original matrix
    print '\n'.join(map(str, Node.denodify(matrix)))


# Example matrix, doesn't necessarily have to be rectangular
example_matrix = [
                    [1, 1, 1, 2],
                    [2, 1, 2, 2],
                    [4, 1, 1, 0],
                    [4, 4, 1, 0]
                 ]
Node.nodify(example_matrix)
all_connected_values(example_matrix)
