subGoalRad 1.5

walls 30

wall 2
10 110 
10 25


wall 4
10 20
110 20
110 45
130 45

wall 3
10 110
35 110
35 127


// Coordinates of first column of seats 

wall 5
10 108
10 103
50 103
50 108
10 108

wall 5
10 98
10 93
45 93
45 98
10 98

wall 5
10 88
10 83
40 83
40 88
10 88

wall 5
10 78
10 73
35 73
35 78
10 78

wall 5
10 68
10 63
30 63
30 68
10 68

wall 5
10 58
10 53
25 53
25 58
10 58

wall 5
10 48
10 43
20 43
20 48
10 48




// Coordinates of last column of seats
wall 5
108 20
103 20
103 60
108 60
108 20


wall 5
98 20
93 20
93 55
98 55
98 20

wall 5
88 20
83 20
83 50
88 50
88 20

wall 5
78 20
73 20
73 45
78 45
78 20

wall 5
68 20
63 20
63 40
68 40
68 20

wall 5
58 20
53 20
53 35
58 35
58 20

wall 5
48 20
43 20
43 30
48 30
48 20





// Coordinates of middle column of seats
wall 5
61 107
58 103
100 66
104 69
61 107


wall 5
55 96
52 92
89 59
92 62
55 96

wall 5
49 86
46 82
78 53
82 56
49 86

wall 5
44 76
41 72
68 48
72 51
44 76


wall 5
40 66
37 62
59 43
63 46
40 66

wall 5
35 56
32 52
48 38
52 41
35 56

wall 5
29 47
26 43
39 33
42 36
29 47




// Coordinates of small room
wall 5 
83 105
75 95
90 81
99 91
83 105







// Coordinates of semi circle wall

wall 29
35 135
40 135
45 135
50 135
55 134
60 133
65 132
70 130
75 128
80 125
85 123
90 120
95 117
100 113
103 110
105 108
108 105
110 102
112 100
115 96
117 94   
120 90
123 85
125 82
127 78
130 72
132 68
134 60
135 45




// Coordinates of double wall
wall 6
10 25
8 25
8 112
33 112
33 127
35 127

wall 7
10 20
8 20
8 18
112 18
112 43
130 43
130 45

wall 34
35 135
33 135
33 137
40 137
45 137
51 137
56 136
61 135
66 134
71 132
76 130
81 127
86 125
91 122
97 119
102 115
105 112
107 110
110 107
112 104
114 102
117 98
119 96   
122 92
125 87
127 84
129 80
132 74
134 70
136 62
137 45
137 43
135 43
135 45


// Coordinates of front desk

wall 5
23 37
19 32
26 27
30 31
23 37



goals 3 
goal
  goalLine 130 45 135 45
  destination 132 42
 next 0 
goal
  goalLine 10 20 10 25  
  destination 7 22     
goal
  goalLine 35 127 35 135
  destination 32 131


peopleBoxes 29
peopleBox
  numPeople 4    
  size .75 .75
  speed 0.45 0.35
  waitTime 10 50                      
  boundBox 12 100 45 101 
  favGoal 2
  stress 10 25
  panic 10 25

peopleBox
  numPeople 2
  size .75 .75
  speed 0.25 0.35
  favGoal 2
  waitTime 10 50
  stress 10 25
  panic 10 25
  boundBox 12 90 40 91

peopleBox
  numPeople 3
  size .75 .75
  speed 0.25 0.5
  favGoal 2
  waitTime 10 50
  stress 20 40
  panic 20 45
  boundBox 12 80 35 81

peopleBox
  numPeople 3
  size .75 .75
  speed 0.25 0.5
  favGoal 1
  waitTime 10 50
  stress 20 40
  panic 20 45
  boundBox 12 70 30 71

peopleBox
  numPeople 4
  size .75 .75
  speed 0.25 0.5
  favGoal 1
  waitTime 10 50
  stress 20 40
  panic 20 45
  boundBox 12 60 25 61

peopleBox
  numPeople 6
  size .75 .75
  speed 0.25 0.5
  favGoal 2
  waitTime 10 50
  stress 20 40
  panic 20 45
  boundBox 100 22 101 54

peopleBox
  numPeople 4
  size .75 .75
  speed 0.25 0.5
  favGoal 2
  waitTime 10 50
  stress 20 40
  panic 20 45
  boundBox 90 22 91 49

peopleBox
  numPeople 3
  size .75 .75
  speed 0.25 0.5
  favGoal 1
  waitTime 10 50
  stress 10 25
  panic 10 25
  boundBox 80 22 81 44

peopleBox
  numPeople 1
  size .75 .75
  speed 0.25 0.5
  favGoal 2
  waitTime 10 50
  stress 10 25
  panic 10 25
  boundBox 57 98 60 98


peopleBox
  numPeople 1
  size .75 .75
  speed 0.25 0.5
  favGoal 2
  waitTime 10 50
  stress 10 25
  panic 10 25
  boundBox 61 95 64 95

peopleBox
  numPeople 1
  size .75 .75
  speed 0.25 0.5
  favGoal 2
  waitTime 10 50
  stress 10 25
  panic 10 25
  boundBox 65 92 68 92

peopleBox
  numPeople 1
  size .75 .75
  speed 0.25 0.5
  favGoal 2
  waitTime 10 50
  stress 10 25
  panic 10 25
  boundBox 69 88 72 88

peopleBox
  numPeople 1
  size .75 .75
  speed 0.25 0.5
  favGoal 2
  waitTime 10 50
  stress 35 40
  panic 10 25
  boundBox 73 85 76 85

peopleBox
  numPeople 1
  size .75 .75
  speed 0.25 0.5
  favGoal 2
  waitTime 10 50
  stress 35 40
  panic 10 25
  boundBox 77 81 80 81


peopleBox
  numPeople 1
  size .75 .75
  speed 0.25 0.5
  favGoal 2
  waitTime 10 50
  stress 35 40
  panic 10 25
  boundBox 81 77 84 77

peopleBox
  numPeople 1
  size .75 .75
  speed 0.25 0.5
  favGoal 2
  waitTime 10 50
  stress 35 40
  panic 10 25
  boundBox 86 73 88 73

peopleBox
  numPeople 1
  size .75 .75
  speed 0.25 0.5
  favGoal 2
  waitTime 10 50
  stress 35 40
  panic 35 50
  boundBox 51 88 54 88


peopleBox
  numPeople 1
  size .75 .75
  speed 0.25 0.5
  favGoal 2
  waitTime 10 50
  stress 35 40
  panic 35 50
  boundBox 55 84 58 84

peopleBox
  numPeople 1
  size .75 .75
  speed 0.25 0.5
  favGoal 2
  waitTime 10 50
  stress 35 40
  panic 35 50
  boundBox 59 80 62 80


peopleBox
  numPeople 1
  size .75 .75
  speed 0.25 0.5
  favGoal 2
  waitTime 10 50
  stress 35 40
  panic 35 50
  boundBox 65 75 68 75


peopleBox
  numPeople 1
  size .75 .75
  speed 0.25 0.5
  favGoal 2
  waitTime 10 50
  stress 35 40
  panic 35 50
  boundBox 46 77 49 77

peopleBox
  numPeople 1
  size .75 .75
  speed 0.25 0.35
  favGoal 2
  waitTime 10 50
  stress 10 25
  panic 10 25
  boundBox 50 74 53 74

peopleBox
  numPeople 1
  size .75 .75
  speed 0.25 0.5
  favGoal 2
  waitTime 10 50
  stress 35 40
  panic 35 50
  boundBox 54 70 57 70

peopleBox
  numPeople 1
  size .75 .75
  speed 0.25 0.35
  favGoal 2
  waitTime 10 50
  stress 10 30
  panic 10 15
  smoke 10 10
  boundBox 58 66 61 66

peopleBox
  numPeople 1
  size .75 .75
  speed 0.25 0.35
  favGoal 2
  waitTime 10 50
  stress 10 25
  panic 10 25
  boundBox 62 63 65 63

peopleBox
  numPeople 1
  size .75 .75
  speed 0.25 0.35
  favGoal 2
  waitTime 10 50
  stress 10 25
  panic 10 25
  boundBox 66 59 69 59

peopleBox
  numPeople 1
  size .75 .75
  speed 0.25 0.5
  favGoal 2
  waitTime 10 50
  stress 10 25
  panic 10 25
  boundBox 42 67 45 67

peopleBox
  numPeople 1
  size .75 .75
  speed 0.25 0.5
  favGoal 2
  waitTime 10 50
  stress 10 25
  panic 10 25
  boundBox 46 63 49 63

peopleBox
  numPeople 1
  size .75 .75
  speed 0.25 0.5
  favGoal 2
  waitTime 10 50
  boundBox 50 60 53 60