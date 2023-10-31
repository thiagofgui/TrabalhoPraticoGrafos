import math
import heapq

class Graph:

    def __init__(self, V: int) -> None:
        '''
        V: int - number of verteces
        E: int - number of edges
        adList: list of list of int - list of neighbors per vertex
        adList: list of list of int - list of weights for each neighbor per vertex
        '''
        self.V = V
        self.E = 0
        self.adjLists = [[] for _ in range(V)]
        self.weightLists = [[] for _ in range(V)]

    def addEdge(self, u: int, v: int, weight: int = 1) -> None:
        '''
        u is the source vertex and v is the destination one
        '''
        self.adjLists[u].append(v)
        self.weightLists[u].append(weight)
        self.E += 1

    #Time and Space O(1) as lists have the len attribute and this algorithm doesn't require extra space
    def getOutDegree(self, u) -> int:
        '''
        returns the number of edges from u
        '''
        return len(self.adjLists[u])

    #Space O(1) since this algorithm doesn't require extra space but Time is O(E) as it has to go through all edges to find out whether the searched one is an end to them or not
    def getInDegree(self, u) -> int:
        '''
        returns the number of edges to u
        '''
        degree = 0
        for adjList in self.adjLists:
            if u in adjList: degree += 1
        return degree

    #Time: O(V+E) = O(V*[const+e]) where e is the number of neighbors of each node, so V*e = E
    #Space: O(V) since we do not add visited neighbors to the Queue
    def BFS(self, u: int) -> list:
        '''
        Breadth-first search from u
        returns a list of vertices accessed from u
        '''
        visitedSet = self.V*[False]
        toVisitQueue = [u]
        visitedSet[u] = True
        BFSPath = []
        while len(toVisitQueue) != 0:
            newU = toVisitQueue.pop(0)
            BFSPath.append(newU)
            for neighbor in self.adjLists[newU]:
                if visitedSet[neighbor] == False:
                    visitedSet[neighbor] = True
                    toVisitQueue.append(neighbor)
        return BFSPath

    def minDistUV(self, u: int, v: int) -> int:
        '''
        Breadth-first search from u to v
        returns a list of vertices accessed from u
        '''
        visitedSet = self.V*[False]
        dist = self.V*[-1]
        toVisitQueue = [u]
        visitedSet[u] = True
        dist[u] = 0
        while len(toVisitQueue) != 0 and dist[v] < 0:
            newU = toVisitQueue.pop(0)
            for neighbor in self.adjLists[newU]:
                if visitedSet[neighbor] == False:
                    visitedSet[neighbor] = True
                    toVisitQueue.append(neighbor)
                    dist[neighbor] = dist[newU]+1
        return dist[v]

    #Time: O(V+E) = O(V*[const+e]) where e is the number of neighbors of each node, so V*e = E
    #Space: O(V) since only call __DFS to unvisited neighbors
    def DFS(self, u: int) -> list:
        '''
        Depth-first search from u
        returns a list of vertices accessed from u
        '''
        visitedSet = self.V*[False]
        DFSPath = []
        self.__DFS(u, visitedSet, DFSPath)
        return DFSPath

    def __DFS(self, u: int, visitedSet: list, DFSPath: list) -> None:
        DFSPath.append(u)
        visitedSet[u] = True
        [self.__DFS(neighbor, visitedSet, DFSPath) for neighbor in self.adjLists[u] if visitedSet[neighbor] == False]

    #Time: O(V+E) = O(V*[const+e]) where e is the number of neighbors of each node, so V*e = E
    #Space: O(V) since we do not add visited neighbors to the Stack
    def DFSStack(self, u: int) -> list:
        '''
        Breadth-first search from u
        returns a list of vertices accessed from u
        '''
        visitedSet = self.V*[False]
        toVisitStack = [u]
        visitedSet[u] = True
        DFSPath = []
        while len(toVisitStack) != 0:
            newU = toVisitStack.pop(-1)
            DFSPath.append(newU)
            for neighbor in self.adjLists[newU]:
                if visitedSet[neighbor] == False:
                    visitedSet[neighbor] = True
                    toVisitStack.append(neighbor)
        return DFSPath

    #O(V² + E)
    def dijkstra(self, u: int) -> list:
        dist = [math.inf]*self.V
        prev = [-1]*self.V
        visitedSet = self.V*[False]
        dist[u] = 0
        prev[u] = u
        while True:
            newU = None
            for node in range(self.V):
                if not visitedSet[node] and (newU is None or dist[node] < dist[newU]):
                    newU = node
            if newU is None or dist[newU] == math.inf:
                break
            visitedSet[newU] = True
            for pos, neighbor in enumerate(self.adjLists[newU]):
                #if visitedSet[neighbor] == False:
                newDist = dist[newU]+self.weightLists[newU][pos]
                if newDist < dist[neighbor]:
                    dist[neighbor] = newDist
                    prev[neighbor] = newU
        return dist

    #O(Vlog(V) + E)
    def dijkstraHeap(self, u: int) -> list:
        dist = [math.inf]*self.V
        prev = [-1]*self.V
        visitedSet = self.V*[False]
        dist[u] = 0
        prev[u] = u
        countVisisted = 0

        myHeap = [(0, u)]
        while countVisisted < self.V:
            _, newU = heapq.heappop(myHeap)
            if not visitedSet[newU]:
                visitedSet[newU] = True
                countVisisted += 1
                for pos, neighbor in enumerate(self.adjLists[newU]):
                    #if visitedSet[neighbor] == False:
                    newDist = dist[newU]+self.weightLists[newU][pos]
                    if newDist < dist[neighbor]:
                        dist[neighbor] = newDist
                        prev[neighbor] = newU
                        heapq.heappush(myHeap, (newDist, neighbor))
        return dist

    #O(V) space complexity and O(E) time complexity
    def topologicalSort(self) -> list:
        inDegree = [0]*self.V #O(V)

        #O(E)
        for adjList in self.adjLists:
            for neighbor in adjList:
                inDegree[neighbor] += 1

        #O(V)
        topSortedList = []
        for neighbor in range(self.V):
            if inDegree[neighbor] == 0:
                topSortedList.append(neighbor)

        i = 0
        while i < len(topSortedList): #O(V)
            node = topSortedList[i]
            i += 1
            for neighbor in self.adjLists[node]: #O(e)
                inDegree[neighbor] -= 1
                if inDegree[neighbor] == 0:
                    topSortedList.append(neighbor)

        return topSortedList

    #minimal spanning tree
    #O(V+E) space complexity for the vertices and the sorted edges
    #O(ElogV) time complexity for sorting and building the MST
    def kruskal(self):

        def _set(groups, node):
            if groups[node] != node:
                #every time that the set of the node's set changes, it recursively looks for the a set referenced that points to itself 
                #and updates the other references throughout the calls
                groups[node] = _set(groups,groups[node])
            return groups[node]

        def _union(groups,srcSet,dstSet):
            groups[srcSet] = dstSet

        groups = list(range(self.V)) #O(V) space
        edges = []
        for src in range(self.V): #O(V)
            for dst, weight in zip(self.adjLists[src], self.weightLists[src]):
                edges.append((weight, src, dst))

        edges.sort() #O(ElogE) = O(ElogV²) = O(ElogV)
        myGraph = Graph(self.V)
        for weight, src, dst in edges: #O(Elog(V)) - logV due to the recursions in the _set method
            srcSet = _set(groups,src)
            dstSet = _set(groups,dst)
            if srcSet != dstSet:
                _union(groups,srcSet,dstSet)
                myGraph.addEdge(src,dst,weight)
                myGraph.addEdge(dst,src,weight)
                if myGraph.E == 2*(self.V-1): #the maximum number of edges in a MST is V-1, so it avoids looking at more edges than necessary
                    return myGraph
        return None

    #minimal spanning tree
    #max heap size is E, so O(V+E) space complexity
    #O(VlogE + VlogE + Ve) = O(VlogV + E) time complexity
    def prim(self):
        nodes = set()
        weights = [math.inf for _ in range(self.V)]
        myHeap = [(0, 0, 0)] #weight, srcNode, dstNode
        weights[0] = 0
        myGraph = Graph(self.V)

        while len(myHeap) > 0: #O(E), but we consider O(V)
            weight, srcNode, dstNode = heapq.heappop(myHeap) #O(logE) = O(logV)
            if dstNode not in nodes: #O(V)
                if srcNode != dstNode:
                    myGraph.addEdge(srcNode,dstNode,weight)
                    myGraph.addEdge(dstNode,srcNode,weight)
                if myGraph.E == 2*(self.V-1): #the maximum number of edges in a MST is V-1, so it avoids looking at more edges than necessary
                    return myGraph
                nodes.add(dstNode)
                for nextNode, nextWeight in zip(self.adjLists[dstNode], self.weightLists[dstNode]): #O(e)
                    if nextWeight < weights[nextNode]:
                        weights[nextNode] = nextWeight
                        heapq.heappush(myHeap, (nextWeight, dstNode, nextNode)) #O(logE) = O(logV)
        return None


class GraphTest():

    def __init__(self, V: int) -> None:
        self.myGraph = Graph(V)

    def addEdge(self, pairs: list, weights: int) -> None:
        assert len(pairs) == len(weights)
        for pair,weight in zip(pairs,weights):
            self.myGraph.addEdge(pair[0], pair[1], weight)

    def checkInDegree(self, u: int, expectedDegree: int) -> None:
        result = self.myGraph.getInDegree(u)
        assert result == expectedDegree, f"Expected in degree of {u} to be {expectedDegree} but it is {result}"

    def checkOutDegree(self, u: int, expectedDegree: int) -> None:
        result = self.myGraph.getOutDegree(u)
        assert result == expectedDegree, f"Expected out degree of {u} to be {expectedDegree} but it is {result}"

    def checkBFS(self, u: int, expectedBFS: list) -> None:
        result = self.myGraph.BFS(u)
        assert result == expectedBFS, f"Expected BFS({u}) to be {expectedBFS} but it is {result}"

    def checkDFS(self, u: int, expectedBFS: list) -> None:
        result = self.myGraph.DFS(u)
        assert result == expectedBFS, f"Expected DFS({u}) to be {expectedBFS} but it is {result}"

    def checkDFSStack(self, u: int, expectedBFS: list) -> None:
        result = self.myGraph.DFSStack(u)
        assert result == expectedBFS, f"Expected checkDFSStack({u}) to be {expectedBFS} but it is {result}"

    def checkMinDistUV(self, uv: list, expectedDist: int) -> None:
        result = self.myGraph.minDistUV(uv[0], uv[1])
        assert result == expectedDist, f"Expected BFS({uv[0]},{uv[1]}) to be {expectedDist} but it is {result}"

    def checkDijkstra(self, u: int, expectedDist: list) -> None: #, expectedPath: list
        #path, dist = self.myGraph.dijkstra(u)
        dist = self.myGraph.dijkstra(u)
        assert dist == expectedDist, f"Expected dijkstra({u}) to be {expectedDist} but it is {dist}"
        #assert path == expectedPath, f"Expected dijkstra({u}) to be {expectedPath} but it is {path}"

    def checkDijkstraHeap(self, u: int, expectedDist: list) -> None: #, expectedPath: list
        #path, dist = self.myGraph.dijkstra(u)
        dist = self.myGraph.dijkstraHeap(u)
        assert dist == expectedDist, f"Expected dijkstra with heap({u}) to be {expectedDist} but it is {dist}"
        #assert path == expectedPath, f"Expected dijkstra({u}) to be {expectedPath} but it is {path}"

    def checkTopologicalSort(self, expectedOrder: list) -> None:
        #path, dist = self.myGraph.dijkstra(u)
        order = self.myGraph.topologicalSort()
        assert order == expectedOrder, f"Expected Topological Sort to be {expectedOrder} but it is {order}"

    def checkKruskal(self, expectedMst) -> None:
        mst = self.myGraph.kruskal()
        mstDijkstra = mst.dijkstra(0)
        expectedMstDijkstra = expectedMst.myGraph.dijkstra(0)
        assert mstDijkstra == expectedMstDijkstra, f"Expected MST Kruskal Dijkstra to be {expectedMstDijkstra} but it is {mstDijkstra}"

    def checkPrim(self, expectedMst) -> None:
        mst = self.myGraph.prim()
        mstDijkstra = mst.dijkstra(0)
        expectedMstDijkstra = expectedMst.myGraph.dijkstra(0)
        assert mstDijkstra == expectedMstDijkstra, f"Expected MST Prim Dijkstra to be {expectedMstDijkstra} but it is {mstDijkstra}"


myTest = GraphTest(8)
myTest.addEdge([[0, 3], [0, 2], [0, 1], [3, 5], [5, 6], [5, 3], [2, 4], [4, 5], [4, 7], [3, 0]], [4, 5, 6, 7, 8, 2, 3, 5, 3, 1])
myTest.checkInDegree(5, 2)
myTest.checkInDegree(0, 1)
myTest.checkOutDegree(2, 1)
myTest.checkOutDegree(0, 3)
myTest.checkBFS(0, [0,3,2,1,5,4,6,7])
myTest.checkBFS(4, [4,5,7,6,3,0,2,1])
myTest.checkDFS(0, [0,3,5,6,2,4,7,1])
myTest.checkDFSStack(0, [0,1,2,4,7,5,6,3])
myTest.checkDFS(4, [4,5,6,3,0,2,1,7])
myTest.checkDFSStack(4, [4,7,5,3,0,1,2,6])
myTest.checkMinDistUV([0, 4], 2)
myTest.checkMinDistUV([2, 7], 2)
myTest.checkMinDistUV([0, 1], 1)
myTest.checkMinDistUV([0, 0], 0)
myTest.checkDijkstra(0, [0, 6, 5, 4, 8, 11, 19, 11])
myTest.checkDijkstraHeap(0, [0, 6, 5, 4, 8, 11, 19, 11])
myTest.checkTopologicalSort([])


myTest2 = GraphTest(3)
myTest2.addEdge([[0, 1], [0, 2], [1, 2]], [5, 3, 2])
myTest2.checkBFS(0, [0,1,2])
myTest2.checkMinDistUV([0, 1], 1)
myTest2.checkDijkstra(0, [0,5,3])
myTest2.checkDijkstraHeap(0, [0,5,3])
myTest2.checkTopologicalSort([0, 1, 2])


#https://jamboard.google.com/d/1c6QhpgiGRR-w-pj6b67vS-jChhGdrFrOcaQlIwjRSSs/viewer?f=1
myTest3 = GraphTest(9)
myTest3.addEdge([[0, 1], [0, 3], [0, 4], [1, 2], [2, 4], [3, 5], [4, 5], [5, 6], [5, 7], [2, 7], [2, 8], [6, 8], \
                 [1, 0], [3, 0], [4, 0], [2, 1], [4, 2], [5, 3], [5, 4], [6, 5], [7, 5], [7, 2], [8, 2], [8, 6]], \
    [2, 3, 5, 5, 2, 8, 7, 4, 3, 5, 6, 11, 2, 3, 5, 5, 2, 8, 7, 4, 3, 5, 6, 11])
expectedMST = GraphTest(9)
expectedMST.addEdge([[0, 1], [0, 3], [0, 4], [2, 4], [5, 6], [5, 7], [2, 7], [8, 2], \
                     [1, 0], [3, 0], [4, 0], [4, 2], [6, 5], [7, 5], [7, 2], [2, 8]], \
    [2, 3, 5, 2, 4, 3, 5, 6, 2, 3, 5, 2, 4, 3, 5, 6])
myTest3.checkKruskal(expectedMST)
myTest3.checkPrim(expectedMST)

#https://www.ic.unicamp.br/~meidanis/courses/mo417/2003s1/aulas/2003-05-16/runPrim.gif
myTest4 = GraphTest(6)
myTest4.addEdge([[0, 1], [1, 2], [2, 3], [3, 4], [4, 5], [5, 0], [1, 5], [5, 3], \
                 [1, 0], [2, 1], [3, 2], [4, 3], [5, 4], [0, 5], [5, 1], [3, 5]], \
    [4, 8, 2, 6, 1, 8, 1, 7, 4, 8, 2, 6, 1, 8, 1, 7])
expectedMST = GraphTest(6)
expectedMST.addEdge([[0, 1], [1, 5], [2, 3], [3, 4], [4, 5], \
                     [1, 0], [5, 1], [3, 2], [4, 3], [5, 4]], \
    [4, 1, 2, 6, 1, 4, 1, 2, 6, 1])
myTest4.checkKruskal(expectedMST)
myTest4.checkPrim(expectedMST)
