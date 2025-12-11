const nums = [1, 2, 3, 4];


// forEach
nums.forEach((num) => {
    console.log("Số:", num);
});

// for (let i = 0; i < nums.length; i++) {
//     console.log("Số :" + nums[i]);
// }

//
// // map
// const doubleNums = nums.map((num) => num * 2);
// console.log("mảng nhân 2 :", doubleNums);
//
//
// //filter
// const evenNums = nums.filter((num) => num % 2 === 0);
// console.log("Số trong mảng chia hết cho 2:", evenNums);
//
//
// //reduce
// const sum = nums.reduce((total, current) => {
//     return total + current;
// }, 0);
// console.log("tổng giá trị của các phần tử trong mảng:", sum);