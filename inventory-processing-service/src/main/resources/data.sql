-- ========================================
-- Insert Products
-- ========================================
INSERT INTO product (
    product_id,
    name,
    description,
    date_of_creation,
    date_of_last_update
) VALUES
(1, 'Paracetamol 500mg', 'Pain relief tablet', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Amoxicillin 250mg', 'Antibiotic capsule', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Vitamin C 1000mg', 'Immune system booster', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ========================================
-- Insert Inventory Batches for Product 1 (Paracetamol)
-- ========================================
INSERT INTO inventory_batch (
    inventory_batch_id,
    batch_nbr,
    quantity_available,
    expiry_date,
    product_id,
    date_of_creation,
    date_of_last_update
) VALUES
(1, 'P500-A1', 200, DATE '2025-12-15', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'P500-A2', 150, DATE '2026-02-10', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'P500-A3', 100, DATE '2025-09-30', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ========================================
-- Insert Inventory Batches for Product 2 (Amoxicillin)
-- ========================================
INSERT INTO inventory_batch (
    inventory_batch_id,
    batch_nbr,
    quantity_available,
    expiry_date,
    product_id,
    date_of_creation,
    date_of_last_update
) VALUES
(4, 'AMX-B1', 120, DATE '2026-03-01', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 'AMX-B2', 200, DATE '2025-11-15', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ========================================
-- Insert Inventory Batches for Product 3 (Vitamin C)
-- ========================================
INSERT INTO inventory_batch (
    inventory_batch_id,
    batch_nbr,
    quantity_available,
    expiry_date,
    product_id,
    date_of_creation,
    date_of_last_update
) VALUES
(6, 'VC-C1', 300, DATE '2027-01-01', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 'VC-C2', 250, DATE '2026-06-10', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 'VC-C3', 400, DATE '2025-08-25', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
