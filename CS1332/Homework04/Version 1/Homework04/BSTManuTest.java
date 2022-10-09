@Test(timeout = TIMEOUT)
    public void testKLargestRandom() {
        int maxTreeSize = 100;
        int minTreeSize = 0;
        Random random = new Random();
        int n = random.nextInt(maxTreeSize + 1 - minTreeSize) + minTreeSize;

        int maxValue = maxTreeSize * 1000;
        int minValue = 0;
        List<Integer> expected = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            int val = random.nextInt(maxValue + 1 - minValue) + maxValue;
            expected.add(val);
            tree.add(val);
        }

        assertEquals(n, tree.size());

        Collections.sort(expected);

        List<Integer> ans = new ArrayList<>();

        int k = random.nextInt(n) + 1;

        for (int i = n - k; i < n; ++i) {
            ans.add(expected.get(i));
        }

        assertEquals(ans, tree.kLargest(k));
    }