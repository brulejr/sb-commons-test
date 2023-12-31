/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2023 Jon Brule <brulejr@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.jrb.labs.commons.test

import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomUtils
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.Instant
import java.util.UUID
import java.util.concurrent.ThreadLocalRandom

interface TestUtils {

    fun randomBigDecimal(min: BigDecimal, max: BigDecimal): BigDecimal {
        val randomBigDecimal = min.add(BigDecimal(Math.random()).multiply(max.subtract(min)))
        return randomBigDecimal.setScale(2, RoundingMode.HALF_DOWN)
    }

    fun randomBigDecimalZeroToTen(): BigDecimal = randomBigDecimal(BigDecimal.ZERO, BigDecimal("10.00"))

    fun randomBoolean() = RandomUtils.nextBoolean()

    fun <E : Enum<E>> randomEnum(enumClass: Class<E>): E {
        val pos = RandomUtils.nextInt(0, enumClass.enumConstants.size - 1)
        return enumClass.enumConstants[pos]
    }

    fun randomGuid(): UUID = UUID.randomUUID()

    fun randomInt() = RandomUtils.nextInt(1, 1000)

    fun <T> randomList(maxSize: Int, supplier: () -> T): List<T> {
        val size = RandomUtils.nextInt(1, maxSize)
        return (1..size).map { supplier.invoke() }
    }

    fun randomLong(): Long = RandomUtils.nextLong(0, 1000L)

    fun <K, V> randomMap(maxSize: Int, keySupplier: () -> K, valueSupplier: () -> V): Map<K, V> {
        val size = RandomUtils.nextInt(1, maxSize)
        return (1..size).associate { keySupplier.invoke() to valueSupplier.invoke() }
    }

    fun <T> randomSet(maxSize: Int, supplier: () -> T): Set<T> {
        val size = RandomUtils.nextInt(1, maxSize)
        return (1..size).map { supplier.invoke() }.toSet()
    }

    fun randomString(): String = RandomStringUtils.randomAlphabetic(10)

    fun randomTimestamp(): Instant = Instant.ofEpochSecond(ThreadLocalRandom.current().nextInt().toLong())

}