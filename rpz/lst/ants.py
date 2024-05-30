def ant_alg(matrix, places, alpha, beta, k_evaporation, days):
    q = calcQ(matrix, places)
    bestWay = []
    minDist = float("inf")
    pheromones = calcPheromones(places)
    visibility = calcVisibility(matrix, places)
    ants = places
    for day in range(days):
        route = np.arange(places)
        visited = calcVisitedPlaces(route, ants)
        for ant in range(ants):
            while (len(visited[ant]) != ants):
                pk = findWays(pheromones, visibility, visited, places, ant, alpha, beta)
                chosenPlace = chooseNextPlaceByPosibility(pk)
                visited[ant].append(chosenPlace - 1)

            visited[ant].append(visited[ant][0])

            curLength = calcLength(matrix, visited[ant])

            if (curLength < minDist):
                minDist = curLength
                bestWay = visited[ant]

        pheromones = updatePheromones(matrix, places, visited, pheromones, q, k_evaporation)

    return minDist, bestWay
